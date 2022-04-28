package com.tianshu.accounts.service;

import com.tianshu.accounts.dao.AccountRepository;
import com.tianshu.accounts.dto.*;
import com.tianshu.accounts.entity.Account;
import com.tianshu.accounts.exception.AccountNotFoundByCustomerIdException;
import com.tianshu.accounts.util.EntityDtoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerDetailsService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoansFeignClient loansFeignClient;

    @Autowired
    private CardsFeignClient cardsFeignClient;

    public CustomerDetailsDto getCustomerDetailsByCustomer(CustomerDto customerDto){
        List<AccountDto> accountDtos = accountService.getAccountsByCustomerId(customerDto.getId());
        List<LoanDto> loanDtos = loansFeignClient.getLoanDetails(customerDto);
        List<CardDto> cardDtos = cardsFeignClient.getCardDetails(customerDto);

        return CustomerDetailsDto.builder()
                .accounts(accountDtos)
                .loans(loanDtos)
                .cards(cardDtos)
                .build();
    }
}
