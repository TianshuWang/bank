package com.tianshu.customers.service;

import com.tianshu.customers.dto.*;
import com.tianshu.customers.service.client.AccountsFeignClient;
import com.tianshu.customers.service.client.CardsFeignClient;
import com.tianshu.customers.service.client.LoansFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerDetailsService {

    @Autowired
    private AccountsFeignClient accountsFeignClient;

    @Autowired
    private LoansFeignClient loansFeignClient;

    @Autowired
    private CardsFeignClient cardsFeignClient;

    public CustomerDetailsDto getCustomerDetailsByCustomer(String correlationId, CustomerDto customerDto){

        List<AccountDto> accountDtos = accountsFeignClient.getAccountDetails(correlationId,customerDto);
        List<LoanDto> loanDtos = loansFeignClient.getLoanDetails(correlationId,customerDto);
        List<CardDto> cardDtos = cardsFeignClient.getCardDetails(correlationId,customerDto);

        return CustomerDetailsDto.builder()
                .accounts(accountDtos)
                .loans(loanDtos)
                .cards(cardDtos)
                .build();
    }

    public CustomerDetailsDto getCustomerDetailsByCustomerFallBack(String correlationId, CustomerDto customerDto){
        log.info("Entry Get Customer's Details Fallback Method");

        List<AccountDto> accountDtos = accountsFeignClient.getAccountDetails(correlationId,customerDto);
        List<CardDto> cardDtos = cardsFeignClient.getCardDetails(correlationId,customerDto);

        return CustomerDetailsDto.builder()
                .accounts(accountDtos)
                .cards(cardDtos)
                .build();
    }
}
