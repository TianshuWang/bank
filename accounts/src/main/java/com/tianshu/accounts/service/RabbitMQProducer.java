package com.tianshu.accounts.service;

import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.mapper.CustomerMapper;
import com.tianshu.accounts.util.EntityDtoUtil;
import lombok.extern.slf4j.Slf4j;
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

    public CustomerDto sendDirect(long customerId) {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        log.info("Accounts service sent direct message of customer's data: {}",customerDto);
        this.rabbitTemplate.convertAndSend(exchangeDirect, routingKey, customerDto);

        return customerDto;
    }

    public CustomerDto sendFanout(long customerId) {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        log.info("Accounts service sent fanout message of customer's data: {}",customerDto);
        this.rabbitTemplate.convertAndSend(exchangeFanout,"",customerDto,correlationData);

        return customerDto;
    }

}
