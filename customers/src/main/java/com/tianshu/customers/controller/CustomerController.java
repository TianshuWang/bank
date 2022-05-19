package com.tianshu.customers.controller;

import com.tianshu.customers.dto.CustomerDto;
import com.tianshu.customers.service.CustomerService;
import com.tianshu.customers.service.KafkaMQProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Customers Controller")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private KafkaMQProducer kafkaMQProducer;

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("All Customers Data")
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @ApiOperation("Customer's details")
    public CustomerDto getCustomerById(@ApiParam("Customer Id") @PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Create Customer")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto){
        return customerService.createCustomer(customerDto);
    }

}
