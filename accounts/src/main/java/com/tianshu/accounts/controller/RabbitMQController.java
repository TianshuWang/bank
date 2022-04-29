package com.tianshu.accounts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.accounts.service.CustomerService;
import com.tianshu.accounts.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "customers/{id}")
    public String produceCustomerById(@PathVariable Long id) {
        rabbitMQService.send(customerService.getCustomerById(id));
        return "Message sent to the RabbitMQ Successfully";
    }
}
