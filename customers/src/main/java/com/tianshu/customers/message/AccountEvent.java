package com.tianshu.customers.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tianshu.customers.dto.AccountDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonPropertyOrder({"accountEventId","accountEventType","accountData"})
public class AccountEvent implements Event{

    @JsonProperty("account_event_id")
    private Integer eventId;

    @JsonProperty("account_event_type")
    private EventType eventType;

    @JsonProperty("account_data")
    private AccountDto data;
}
