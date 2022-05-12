package com.tianshu.accounts.service;

import com.tianshu.accounts.message.CustomerData;
import com.tianshu.accounts.util.EntityDtoUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class RabbitMQProducer {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange-direct}")
    private String exchangeDirect;

    @Value("${spring.rabbitmq.exchange-fanout}")
    private String exchangeFanout;

    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;

    @Autowired
    private CustomerService customerService;

    public CustomerData sendDirect(long customerId) {
        CustomerData customerData = EntityDtoUtil.dtoToEntity(customerService.getCustomerById(customerId), CustomerData.class);
        log.info("Accounts service sent direct message of customer's data: {}",customerData);
        this.rabbitTemplate.convertAndSend(exchangeDirect, routingKey, customerData);

        return customerData;
    }

    public CustomerData sendFanout(long customerId) {
        CustomerData customerData = EntityDtoUtil.dtoToEntity(customerService.getCustomerById(customerId), CustomerData.class);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("Accounts service sent fanout message of customer's data: {}",customerData);
        this.rabbitTemplate.convertAndSend(exchangeFanout,"",customerData,correlationData);

        return customerData;
    }

}
