package com.tianshu.customers.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.customers.dao.CustomerRepository;
import com.tianshu.customers.dto.CustomerDto;
import com.tianshu.customers.message.CustomerEvent;
import com.tianshu.customers.message.Event;
import com.tianshu.customers.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.poi.ss.formula.functions.T;
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
public abstract class KafkaProducer {

    @Autowired
    protected KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    protected ObjectMapper objectMapper;



    @Value("${spring.kafka.topicCards}")
    private String topicCards;

    @Value("${spring.kafka.topicLoans}")
    private String topicLoans;

    public abstract void sendProducerRecord(Event event) throws JsonProcessingException;

    protected abstract void buildEvent();

    public void sendProducerRecord(Event event, String topic) throws JsonProcessingException {
        Integer key = event.getEventId();
        String value = objectMapper.writeValueAsString(event);

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
    }

    protected ProducerRecord<Integer, String> buildProducerRecord(Integer key, String value, String topic) {
        List<Header> recordHeaders = Stream.of(new RecordHeader("event-source", "scanner".getBytes())).collect(Collectors.toList());

        return new ProducerRecord<>(topic,null,key,value,recordHeaders);
    }

    protected void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception: {} ",ex.getMessage());
        try{
            throw ex;
        }
        catch(Throwable throwable){
            log.error("Error in OnFailure: {} ",throwable.getMessage());
        }
    }

    protected void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message Sent Successfully for the key: {} and value: {}, partition: {}", key, value, result.getRecordMetadata().partition());
    }


}
