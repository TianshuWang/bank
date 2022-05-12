package com.tianshu.accounts.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "CustomerEvent"
})
public class CustomerEvent {

    private Integer customerEventId;
    private CustomerEventType customerEventType;
    private CustomerData customerData;
}
