package com.tianshu.cards.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createDate;
}
