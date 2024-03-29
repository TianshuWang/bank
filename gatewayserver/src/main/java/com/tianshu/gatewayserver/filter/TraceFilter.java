package com.tianshu.gatewayserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.UUID;

@Order(1)
@Component
public class TraceFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(TraceFilter.class);

    @Autowired
    private FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        if(isCorrelationIdPresent(requestHeaders)){
            logger.debug("Bank-correlation-id found in tracing filter: {}.", filterUtility.getCorrelationId(requestHeaders));
        }
        else{
            String correlationId = this.generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationId);
            logger.debug("Bank-correlation-id generated in tracing filter: {}.", correlationId);
        }

        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders){
        return filterUtility.getCorrelationId(requestHeaders) != null;
    }

    private String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }
}
