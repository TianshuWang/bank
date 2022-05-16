package com.tianshu.accounts.mapper;

import com.tianshu.accounts.dto.AccountDto;
import com.tianshu.accounts.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto entityToDto(Account account);

    Account dtoToEntity(AccountDto accountDto);

    List<AccountDto> entityListToDtoList(List<Account> accounts);
}
