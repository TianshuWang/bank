package com.tianshu.customers.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class ResponseException {

    private Date timestamp;
    private String message;
    private String details;
}
