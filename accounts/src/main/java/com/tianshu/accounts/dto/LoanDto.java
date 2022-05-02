package com.tianshu.accounts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
}
