package com.tianshu.customers.controller;

import com.tianshu.customers.dto.CustomerDetailsDto;
import com.tianshu.customers.dto.CustomerDto;
import com.tianshu.customers.service.CustomerDetailsService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Customer's Details Controller")
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @PostMapping("/customer-details")
    @ResponseStatus(HttpStatus.FOUND)
    @ApiOperation("Customer's details")
    @CircuitBreaker(name = "circuitbreakerForCustomerDetails", fallbackMethod = "getCustomerDetailsFallBack")
    @Retry(name = "retryForCustomerDetails", fallbackMethod = "getCustomerDetailsFallBack")
    public CustomerDetailsDto getCustomerDetails(@RequestHeader("bank-correlation-id") String correlationId, @RequestBody CustomerDto customerDto){
        return customerDetailsService.getCustomerDetailsByCustomer(correlationId, customerDto);
    }

    private CustomerDetailsDto getCustomerDetailsFallBack(String correlationId, CustomerDto customerDto, Throwable t){
        return customerDetailsService.getCustomerDetailsByCustomerFallBack(correlationId,customerDto);
    }

    @GetMapping("/hello")
    @RateLimiter(name = "rateLimiterForHello", fallbackMethod = "helloFallBack")
    public String hello(){
        return "hhhhhhhhhhhello k8s";
    }

    private String helloFallBack(Throwable t){
        return "helloooooooooooooo k8s";
    }
}
