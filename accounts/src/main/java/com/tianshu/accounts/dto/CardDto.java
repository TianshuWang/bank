package com.tianshu.accounts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@JsonPropertyOrder({"id","customerId","cardNumber","type","totalLimit","amountUsed","availableAmount","createDate"})
public class CardDto {

    @JsonProperty("card_id")
    private Long id;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("card_number")
    private Long cardNumber;

    @JsonProperty("card_type")
    private String type;

    @JsonProperty("total_limit")
    private Double totalLimit;

    @JsonProperty("amount_used")
    private Double amountUsed;

    @JsonProperty("available_amount")
    private Double availableAmount;

    @JsonProperty("create_date")
    private Date createDate;
}
