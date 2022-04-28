package com.tianshu.accounts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class CustomerDetailsDto {

    private List<AccountDto> accounts;
    private List<LoanDto> loans;
    private List<CardDto> cards;
}
