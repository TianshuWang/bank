package com.tianshu.accounts.service;

import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.dto.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("loans-service")
public interface LoansFeignClient {

    @PostMapping("loans/customer")
    List<LoanDto> getLoanDetails(@RequestHeader("bank-correlation-id") String correlationId, @RequestBody CustomerDto customerDto);
}
