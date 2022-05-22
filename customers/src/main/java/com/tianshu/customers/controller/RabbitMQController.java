package com.tianshu.customers.controller;


import com.tianshu.customers.dto.CustomerDto;
import com.tianshu.customers.service.RabbitMQProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("rabbitmq")
@Api(tags = "Customers RabbitMQ Controller")
public class RabbitMQController {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;


    @GetMapping(value = "/direct/customer/{id}")
    @ApiOperation("Produce Customer's data directly")
    public ResponseEntity<CustomerDto> produceCustomerDirectById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(rabbitMQProducer.sendDirect(id));
    }

    @GetMapping(value = "/fanout/customer/{id}")
    @ApiOperation("Produce Customer's data fanoutly")
    public ResponseEntity<CustomerDto> produceCustomerFanoutById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(rabbitMQProducer.sendFanout(id));
    }
}
