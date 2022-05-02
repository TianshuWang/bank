package com.tianshu.accounts.controller;

import com.tianshu.accounts.dto.CustomerDetailsDto;
import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.service.CustomerDetailsService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("customer-details")
@Api(tags = "Customer's Details Controller")
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Customer's details")
    //@CircuitBreaker(name = "circuitbreakerForCustomerDetails", fallbackMethod = "getCustomerDetailsFallBack")
    @Retry(name = "retryForCustomerDetails", fallbackMethod = "getCustomerDetailsFallBack")
    public CustomerDetailsDto getCustomerDetails(@RequestBody CustomerDto customerDto){
        return customerDetailsService.getCustomerDetailsByCustomer(customerDto);
    }

    private CustomerDetailsDto getCustomerDetailsFallBack(CustomerDto customerDto, Throwable t){
        return customerDetailsService.getCustomerDetailsByCustomerFallBack(customerDto);
    }

    @GetMapping("hello")
    @RateLimiter(name = "rateLimiterForHello", fallbackMethod = "helloFallBack")
    public String hello(){
        return "hhhhhhhhhhhello";
    }

    private String helloFallBack(Throwable t){
        return "helloooooooooooooo";
    }
}
