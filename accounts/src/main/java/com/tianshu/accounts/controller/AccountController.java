package com.tianshu.accounts.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.accounts.config.AccountServiceConfig;
import com.tianshu.accounts.dto.AccountDto;
import com.tianshu.accounts.entity.Properties;
import com.tianshu.accounts.service.AccountService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@Api(tags = "Accounts Controller")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("accounts/customer/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @ApiOperation("Customer's accounts details")
    @Timed(value = "getCustomerAccountsDetails.time", description = "Time taken to return Customer's Accounts details")
    public List<AccountDto> getAccountsDetailsByCustomerId(@ApiParam("Customer Id") @PathVariable Long id){
        return accountService.getAccountsDetailsByCustomerId(id);
    }

    @GetMapping("accounts/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @ApiOperation("Account's details")
    public AccountDto getAccountsDetailsById(@ApiParam("Account Id") @PathVariable Long id){
        return accountService.getAccountDetailsById(id);
    }

    @GetMapping("accounts/properties")
    @ResponseStatus(HttpStatus.FOUND)
    @ApiOperation("Account's properties")
    public String getPropertyDetails() throws JsonProcessingException {
        return accountService.getPropertyDetails();
    }
}
