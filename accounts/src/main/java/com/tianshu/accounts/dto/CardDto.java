package com.tianshu.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CardDto {

    private Long id;
    private Long customerId;
    private Long cardNumber;
    private String type;
    private Double totalLimit;
    private Double amountUsed;
    private Double availableAmount;
    private LocalDate createDate;
}
