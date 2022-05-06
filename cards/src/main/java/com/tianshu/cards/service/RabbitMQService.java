package com.tianshu.cards.service;

import com.tianshu.cards.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQService {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listenDirect(CustomerDto customerDto) {
        logger.info("Cards service received direct message of customer's data: {}",customerDto.toString());
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-cards}")
    public void listenFanout(CustomerDto customerDto) {
        logger.info("Cards service received fanout message of customer's data: {}",customerDto.toString());
    }
}
