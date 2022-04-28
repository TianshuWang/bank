package com.tianshu.accounts.controller;

import com.tianshu.accounts.dto.CustomerDetailsDto;
import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.service.CustomerDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("customer-details")
@Api(tags = "Customer's Details Controller")
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Customer's details")
    public CustomerDetailsDto getCustomerDetails(@RequestBody CustomerDto customerDto){
        return customerDetailsService.getCustomerDetailsByCustomer(customerDto);
    }
}
