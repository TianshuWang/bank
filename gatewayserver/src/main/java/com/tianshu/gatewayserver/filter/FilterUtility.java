package com.tianshu.gatewayserver.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.Optional;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "bank-correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders){
        List<String> requestHeadersList = requestHeaders.get(CORRELATION_ID);

        return Optional.ofNullable(requestHeadersList).isPresent() ?
                requestHeadersList.stream().findFirst().get():
                null;
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value){
        return exchange.mutate().request(exchange.getRequest().mutate().header(name,value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange,String correlationId){
        return this.setRequestHeader(exchange,CORRELATION_ID,correlationId);
    }
}
