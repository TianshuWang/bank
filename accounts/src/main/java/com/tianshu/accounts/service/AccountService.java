package com.tianshu.accounts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.accounts.config.AccountServiceConfig;
import com.tianshu.accounts.dao.AccountRepository;
import com.tianshu.accounts.dto.AccountDto;
import com.tianshu.accounts.entity.Account;
import com.tianshu.accounts.entity.Properties;
import com.tianshu.accounts.exception.AccountNotFoundByCustomerIdException;
import com.tianshu.accounts.util.EntityDtoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceConfig accountConfig;

    public List<AccountDto> getAccountsByCustomerId(Long customerId){
        List<Account> accounts = accountRepository.findByCustomerId(customerId);

        if(CollectionUtils.isEmpty(accounts)){
            throw new AccountNotFoundByCustomerIdException(customerId);
        }

        List<AccountDto> accountsDto = new ArrayList<>();
        accounts.forEach(a -> accountsDto.add(EntityDtoUtil.entityToDto(a, AccountDto.class)));

        return accountsDto;
    }

    public AccountDto getAccountById(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found by Id:" + id));

        return EntityDtoUtil.entityToDto(account, AccountDto.class);
    }

    public String getPropertyDetails() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties(accountConfig.getMsg(),accountConfig.getBuildVersion()
                ,accountConfig.getMailDetails(),accountConfig.getActiveBranches());

        return objectMapper.writeValueAsString(properties);
    }

}
