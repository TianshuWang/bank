package com.tianshu.customers.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.queue-loans}")
    private String queueLoans;

    @Value("${spring.rabbitmq.queue-cards}")
    private String queueCards;

    @Value("${spring.rabbitmq.queue}")
    private String queueName;

    @Value("${spring.rabbitmq.exchange-direct}")
    private String exchangeDirect;

    @Value("${spring.rabbitmq.exchange-fanout}")
    private String exchangeFanout;

    @Value("${spring.rabbitmq.routingKey}")
    private String routingKey;

    @Bean("directQueue")
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean("fanoutQueueLoans")
    public Queue queueLoans() {
        return new Queue(queueLoans, false);
    }

    @Bean("fanoutQueueCards")
    public Queue queueCards() {
        return new Queue(queueCards, false);
    }

    @Bean("directExchange")
    public DirectExchange exchange() {
        return new DirectExchange(exchangeDirect);
    }

    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(exchangeFanout);
    }

    @Bean("directBinding")
    public Binding binding(@Qualifier("directQueue")Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean("fanoutLoansBinding")
    public Binding fanoutLoansBinding(@Qualifier("fanoutQueueLoans")Queue queue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean("fanoutCardsBinding")
    public Binding fanoutCardsBinding(@Qualifier("fanoutQueueCards")Queue queue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
