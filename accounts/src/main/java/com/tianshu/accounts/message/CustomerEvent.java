package com.tianshu.accounts.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tianshu.accounts.dto.CustomerDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CustomerEvent {

    @JsonProperty("customer_event_id")
    private Integer customerEventId;

    @JsonProperty("customer_event_type")
    private CustomerEventType customerEventType;

    @JsonProperty("customer_data")
    private CustomerDto customerData;
}
