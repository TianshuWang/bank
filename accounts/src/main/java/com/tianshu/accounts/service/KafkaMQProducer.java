package com.tianshu.accounts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.accounts.dao.CustomerRepository;
import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.message.CustomerEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class KafkaMQProducer {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    public CustomerEvent sendCustomerEvent(CustomerEvent customerEvent) throws JsonProcessingException {
        this.buildCustomerEvent(customerEvent);

        Integer key = customerEvent.getCustomerEventId();
        String value = objectMapper.writeValueAsString(customerEvent);

        ListenableFuture<SendResult<Integer,String>> listenableFuture = kafkaTemplate.sendDefault(key,value);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key, value, result);
            }
        });

        return customerEvent;
    }

    public CustomerEvent sendCustomerEventSynchronous(CustomerEvent customerEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
        this.buildCustomerEvent(customerEvent);

        Integer key = customerEvent.getCustomerEventId();
        String value = objectMapper.writeValueAsString(customerEvent);

        SendResult<Integer,String> sendResult = null;
        try {
            sendResult= kafkaTemplate.sendDefault(key,value).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("InterruptedException/ExecutionException Sending the Message and the exception: {} ",e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Exception Sending the Message and the exception: {} ",e.getMessage());
            throw e;
        }

        log.info("Message Sent Successfully Synchronously, SendResult: {}",sendResult);
        return customerEvent;
    }

    public CustomerEvent sendCustomerEventProducerRecord(CustomerEvent customerEvent) throws JsonProcessingException{
        this.buildCustomerEvent(customerEvent);

        Integer key = customerEvent.getCustomerEventId();
        String value = objectMapper.writeValueAsString(customerEvent);

        ProducerRecord<Integer,String> producerRecord = buildProducerRecord(key,value,topic);
        ListenableFuture<SendResult<Integer,String>> listenableFuture = kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, value, ex);
            }
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key, value, result);
            }
        });

        return customerEvent;
    }

    private void buildCustomerEvent(CustomerEvent customerEvent){
        CustomerDto customerDto = "NEW".equalsIgnoreCase(customerEvent.getCustomerEventType().toString()) ?
                customerService.createCustomer(customerEvent.getCustomerData()) :
                customerService.updateCustomer(customerEvent.getCustomerData());

        customerEvent.setCustomerData(customerDto);
    }

    private ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {
        List<Header> recordHeaders = Stream.of(new RecordHeader("event-source", "scanner".getBytes())).collect(Collectors.toList());

        return new ProducerRecord<>(topic,null,key,value,recordHeaders);
    }

    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception: {} ",ex.getMessage());
        try{
            throw ex;
        }
        catch(Throwable throwable){
            log.error("Error in OnFailure: {} ",throwable.getMessage());
        }
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message Sent Successfully for the key: {} and value: {}, partition: {}", key, value, result.getRecordMetadata().partition());
    }


}
