package com.tianshu.accounts.service;

import com.tianshu.accounts.dto.CustomerDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;

    public void send(CustomerDto customerDto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, customerDto);
    }
}
