package com.tianshu.customers.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tianshu.customers.dto.CustomerDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonPropertyOrder({"customerEventId","customerEventType","customerData"})
public class CustomerEvent {

    public enum CustomerEventType {
        NEW, UPDATE
    }
    @JsonProperty("customer_event_id")
    private Integer customerEventId;

    @JsonProperty("customer_event_type")
    private CustomerEventType customerEventType;

    @JsonProperty("customer_data")
    private CustomerDto customerData;
}
