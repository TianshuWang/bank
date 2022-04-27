package com.tianshu.loans.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class LoanDto {

    private Long id;
    private Long customerId;
    private LocalDate startDate;
    private String type;
    private Double total;
    private Double amountPaid;
    private Double outstandingAmount;
    private LocalDate createDate;
}
