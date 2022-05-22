package com.tianshu.customers.service.client;

import com.tianshu.customers.dto.AccountDto;
import com.tianshu.customers.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("accounts")
public interface AccountsFeignClient {

    @PostMapping("/accounts/customer")
    List<AccountDto> getAccountDetails(@RequestHeader("bank-correlation-id") String correlationId, @RequestBody CustomerDto customerDto);
}
