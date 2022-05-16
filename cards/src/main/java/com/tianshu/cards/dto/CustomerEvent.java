package com.tianshu.cards.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonPropertyOrder({"customerEventId","customerEventType","customerData"})
public class CustomerEvent {

    @JsonProperty("customer_event_id")
    private Integer customerEventId;

    @JsonProperty("customer_event_type")
    private CustomerEventType customerEventType;

    @JsonProperty("customer_data")
    private CustomerDto customerData;
}
