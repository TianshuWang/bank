package com.tianshu.accounts.service;

import com.tianshu.accounts.dto.CardDto;
import com.tianshu.accounts.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {

    @PostMapping("cards/customer")
    List<CardDto> getCardDetails(@RequestBody CustomerDto customerDto);
}
