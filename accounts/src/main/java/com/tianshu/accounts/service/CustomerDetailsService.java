package com.tianshu.accounts.service;

import com.tianshu.accounts.dto.*;
import com.tianshu.accounts.service.client.CardsFeignClient;
import com.tianshu.accounts.service.client.LoansFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDetailsService.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoansFeignClient loansFeignClient;

    @Autowired
    private CardsFeignClient cardsFeignClient;

    public CustomerDetailsDto getCustomerDetailsByCustomer(String correlationId, CustomerDto customerDto){
        logger.info("Get Customer's Details Method Started");

        List<AccountDto> accountDtos = accountService.getAccountsByCustomerId(customerDto.getId());
        List<LoanDto> loanDtos = loansFeignClient.getLoanDetails(correlationId,customerDto);
        List<CardDto> cardDtos = cardsFeignClient.getCardDetails(correlationId,customerDto);

        logger.info("Get Customer's Details Method Ended");

        return CustomerDetailsDto.builder()
                .accounts(accountDtos)
                .loans(loanDtos)
                .cards(cardDtos)
                .build();
    }

    public CustomerDetailsDto getCustomerDetailsByCustomerFallBack(String correlationId, CustomerDto customerDto){
        List<AccountDto> accountDtos = accountService.getAccountsByCustomerId(customerDto.getId());
        List<CardDto> cardDtos = cardsFeignClient.getCardDetails(correlationId,customerDto);

        return CustomerDetailsDto.builder()
                .accounts(accountDtos)
                .cards(cardDtos)
                .build();
    }
}
