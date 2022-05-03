package com.tianshu.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p.path("/bank/accounts-service/**")
                        .filters(f -> f.rewritePath("/bank/accounts-service/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time",new Date().toString()))
                        .uri("lb://ACCOUNTS-SERVICE"))
                .route(p -> p.path("/bank/loans-service/**")
                        .filters(f -> f.rewritePath("/bank/loans-service/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time",new Date().toString()))
                        .uri("lb://LOANS-SERVICE"))
                .route(p -> p.path("/bank/cards-service/**")
                        .filters(f -> f.rewritePath("/bank/cards-service/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time",new Date().toString()))
                        .uri("lb://CARDS-SERVICE"))
                .build();
    }
}
