package com.tianshu.loans.service;

import com.tianshu.loans.message.CustomerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listenDirect(CustomerData customerData) {
        logger.info("Loans service received direct message of customer's data: {}",customerData.toString());
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue-loans}")
    public void listen(CustomerData customerData) {
        logger.info("Loans service received fanout message of customer's data: {}",customerData.toString());
    }
}
