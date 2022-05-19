package com.tianshu.accounts.service;


import com.tianshu.accounts.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQConsumer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listenDirect(CustomerDto customerDto) {
        log.info("Accounts service received direct message of customer's data: {}",customerDto.toString());
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-loans}")
    public void listen(CustomerDto customerDto) {
        log.info("Accounts service received fanout message of customer's data: {}",customerDto.toString());
    }
}
