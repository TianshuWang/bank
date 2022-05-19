package com.tianshu.customers.dto;

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
@JsonPropertyOrder({"id","customerId","startDate","type","total","amountPaid","outstandingAmount","createDate"})
public class LoanDto {

    @JsonProperty("loan_id")
    private Long id;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("loan_type")
    private String type;

    @JsonProperty("total")
    private Double total;

    @JsonProperty("amount_paid")
    private Double amountPaid;

    @JsonProperty("outstanding_amount")
    private Double outstandingAmount;

    @JsonProperty("create_date")
    private Date createDate;
}
