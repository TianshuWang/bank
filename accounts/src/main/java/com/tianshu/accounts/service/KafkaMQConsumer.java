package com.tianshu.accounts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.accounts.dto.AccountDto;
import com.tianshu.accounts.entity.Account;
import com.tianshu.accounts.mapper.AccountMapper;
import com.tianshu.accounts.message.AccountEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMQConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountService accountService;

    @KafkaListener(topics = {"${spring.kafka.topic}"})
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        log.info("ConsumerRecord: {}", consumerRecord);

        AccountEvent accountEvent = objectMapper.readValue(consumerRecord.value(), AccountEvent.class);

        AccountDto accountDto = "NEW".equalsIgnoreCase(accountEvent.getEventType().name()) ?
                accountService.createAccount(accountEvent.getData()) :
                accountService.updateAccount(accountEvent.getData());

        log.info("Account of customer:{} was saved with Id:{}",accountDto.getCustomerId(),accountDto.getId());
    }
}
