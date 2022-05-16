package com.tianshu.loans.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMQConsumer {

    @Value("${spring.kafka.topic}")
    private String topic;

    @KafkaListener(topics = {"customer-events"})
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord){
        log.info("ConsumerRecord: {}", consumerRecord);
    }
}
