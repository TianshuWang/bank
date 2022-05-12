package com.tianshu.accounts.controller;


import com.tianshu.accounts.message.CustomerData;
import com.tianshu.accounts.service.RabbitMQProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("rabbitmq")
@Api(tags = "Accounts RabbitMQ Controller")
public class RabbitMQController {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;


    @GetMapping(value = "direct/customers/{id}")
    @ApiOperation("Produce Customer's data directly")
    public ResponseEntity<CustomerData> produceCustomerDirectById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(rabbitMQProducer.sendDirect(id));
    }

    @GetMapping(value = "fanout/customers/{id}")
    @ApiOperation("Produce Customer's data fanoutly")
    public ResponseEntity<CustomerData> produceCustomerFanoutById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(rabbitMQProducer.sendFanout(id));
    }
}
