package com.tianshu.customers.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.tianshu.customers.message.CustomerEvent;
import com.tianshu.customers.service.KafkaMQProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("kafka")
@Api(tags = "Accounts Kafka Controller")
public class KafkaController {

    @Autowired
    private KafkaMQProducer kafkaMQProducer;

    @PostMapping(value = "/customer")
    @ApiOperation("Produce Customer's data")
    public ResponseEntity<CustomerEvent> produceCustomer(@RequestBody CustomerEvent customerEvent ) throws JsonProcessingException, ExecutionException, InterruptedException {

        customerEvent.setCustomerEventType(CustomerEvent.CustomerEventType.NEW);
        //return ResponseEntity.status(HttpStatus.OK).body(kafkaMQProducer.sendCustomerEvent(customerEvent));
        //return ResponseEntity.status(HttpStatus.OK).body(kafkaMQProducer.sendCustomerEventSynchronous(customerEvent));
        return ResponseEntity.status(HttpStatus.CREATED).body(kafkaMQProducer.sendCustomerEventProducerRecord(customerEvent));
    }

    @PutMapping(value = "/customer")
    @ApiOperation("Update Customer's data")
    public ResponseEntity<CustomerEvent> updateCustomer(@RequestBody CustomerEvent customerEvent ) throws JsonProcessingException, ExecutionException, InterruptedException {

        customerEvent.setCustomerEventType(CustomerEvent.CustomerEventType.UPDATE);
        //return ResponseEntity.status(HttpStatus.OK).body(kafkaMQProducer.sendCustomerEvent(customerEvent));
        //return ResponseEntity.status(HttpStatus.OK).body(kafkaMQProducer.sendCustomerEventSynchronous(customerEvent));
        return ResponseEntity.status(HttpStatus.OK).body(kafkaMQProducer.sendCustomerEventProducerRecord(customerEvent));
    }
}
