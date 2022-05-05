package com.tianshu.accounts.service;

import com.tianshu.accounts.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class RabbitMQService {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange-direct}")
    private String exchangeDirect;

    @Value("${spring.rabbitmq.exchange-fanout}")
    private String exchangeFanout;

    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;

    public void sendDirect(CustomerDto customerDto) {
        logger.info("Accounts service sent direct message of customer's data: {}",customerDto);
        this.rabbitTemplate.convertAndSend(exchangeDirect, routingKey, customerDto);
    }

    public void sendFanout(CustomerDto customerDto) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        logger.info("Accounts service sent fanout message of customer's data: {}",customerDto);
        this.rabbitTemplate.convertAndSend(exchangeFanout,"",customerDto,correlationData);
    }

}
