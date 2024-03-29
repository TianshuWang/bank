package com.tianshu.customers.service.client;

import com.tianshu.customers.dto.CustomerDto;
import com.tianshu.customers.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {

    @PostMapping("/loans/customer")
    List<LoanDto> getLoanDetails(@RequestHeader("bank-correlation-id") String correlationId, @RequestBody CustomerDto customerDto);
}
