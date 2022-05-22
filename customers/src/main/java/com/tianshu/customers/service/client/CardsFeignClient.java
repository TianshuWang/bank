package com.tianshu.customers.service.client;

import com.tianshu.customers.dto.CardDto;
import com.tianshu.customers.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {

    @PostMapping("/cards/customer")
    List<CardDto> getCardDetails(@RequestHeader("bank-correlation-id") String correlationId, @RequestBody CustomerDto customerDto);
}
