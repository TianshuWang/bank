package com.tianshu.loans.service;

import com.tianshu.loans.dto.CustomerDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(CustomerDto customerDto) {
        System.out.println(customerDto);
    }
}
