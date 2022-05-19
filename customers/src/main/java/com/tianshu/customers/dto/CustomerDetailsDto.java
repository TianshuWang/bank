package com.tianshu.customers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("accounts")
    private List<AccountDto> accounts;

    @JsonProperty("loans")
    private List<LoanDto> loans;

    @JsonProperty("cards")
    private List<CardDto> cards;
}
