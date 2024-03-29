package com.tianshu.customers.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"id","customerId","type","branchAddress","createDate"})
public class AccountDto {

    @JsonProperty("account_id")
    private Long id;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("account_type")
    private String type;

    @JsonProperty("branch_address")
    private String branchAddress;

    @JsonProperty("create_date")
    private Date createDate;
}
