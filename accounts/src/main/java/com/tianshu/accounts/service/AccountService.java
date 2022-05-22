package com.tianshu.accounts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.accounts.config.AccountServiceConfig;
import com.tianshu.accounts.dao.AccountRepository;
import com.tianshu.accounts.dto.AccountDto;
import com.tianshu.accounts.entity.Account;
import com.tianshu.accounts.entity.Properties;
import com.tianshu.accounts.exception.AccountException;
import com.tianshu.accounts.mapper.AccountMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceConfig accountConfig;

    @Autowired
    private AccountMapper accountMapper;

    public List<AccountDto> getAccountsDetailsByCustomerId(Long customerId){
        List<Account> accounts = accountRepository.findByCustomerId(customerId);

        if(CollectionUtils.isEmpty(accounts)){
            throw new EntityNotFoundException(String.format("Not found accounts of the customer %s",customerId));
        }

        return accountMapper.entityListToDtoList(accounts);
    }

    public AccountDto getAccountDetailsById(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account not found by Id:" + id));

        return accountMapper.entityToDto(account);
    }

    public String getPropertyDetails() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties(accountConfig.getMsg(),accountConfig.getBuildVersion()
                ,accountConfig.getMailDetails(),accountConfig.getActiveBranches());

        return objectMapper.writeValueAsString(properties);
    }

    public AccountDto createAccount(AccountDto accountDto){
        Account account = accountMapper.dtoToEntity(accountDto);

        return accountMapper.entityToDto(accountRepository.save(account));
    }
    public AccountDto updateAccount(AccountDto accountDto){
        Optional.ofNullable(accountRepository.findByIdAndCustomerId(accountDto.getId(),accountDto.getCustomerId()))
                .orElseThrow(() -> new AccountException(String.format("Not found account:%s of the customer:%s",accountDto.getId(),accountDto.getCustomerId())));

        Account account = accountMapper.dtoToEntity(accountDto);

        return accountMapper.entityToDto(accountRepository.save(account));
    }


}
