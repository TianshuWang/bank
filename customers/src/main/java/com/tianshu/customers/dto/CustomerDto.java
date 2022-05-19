package com.tianshu.customers.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonRootName("Customer")
@JsonPropertyOrder({"id","name","email","mobileNumber","createDate"})
public class CustomerDto {

    @JsonProperty("customer_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("create_date")
    private Date createDate;
}
