package com.tianshu.accounts.controller;


import com.tianshu.accounts.service.CustomerService;
import com.tianshu.accounts.service.RabbitMQService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("rabbitmq")
@Api(tags = "Accounts RabbitMQ Controller")
public class RabbitMQController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "direct/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Produce Customer's details")
    public String produceCustomerDirectById(@PathVariable Long id) {
        rabbitMQService.sendDirect(customerService.getCustomerById(id));
        return "Message sent to the RabbitMQ Successfully";
    }

    @GetMapping(value = "fanout/customers/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Produce Customer's details")
    public String produceCustomerFanoutById(@PathVariable Long id) {
        rabbitMQService.sendFanout(customerService.getCustomerById(id));
        return "Message sent to the RabbitMQ Successfully";
    }
}
