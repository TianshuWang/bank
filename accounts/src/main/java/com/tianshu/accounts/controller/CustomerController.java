package com.tianshu.accounts.controller;

import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
@Api(tags = "Customers Controller")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Customers's details")
    public List<CustomerDto> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Customer's details")
    public CustomerDto getCustomerById(@ApiParam("Customer Id") @PathVariable Long id){
        return customerService.getCustomerById(id);
    }

}
