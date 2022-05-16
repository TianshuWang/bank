package com.tianshu.cards;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.cards.dto.CustomerEvent;
import com.tianshu.cards.service.KafkaMQConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest()
@EmbeddedKafka(topics = {"customer-events"}, partitions = 3)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
"spring.kafka.consumer.bootstrap-servers=${spring.embedded.kafka.brokers}"})
class CustomerEventsIntegrationTest {

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    KafkaListenerEndpointRegistry endpointRegistry;

    @SpyBean
    KafkaMQConsumer kafkaMQConsumerSpy;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        for(MessageListenerContainer messageListenerContainer: endpointRegistry.getAllListenerContainers()){
            ContainerTestUtils.waitForAssignment(messageListenerContainer, embeddedKafkaBroker.getPartitionsPerTopic());
        }
    }

    @Test
    void publishNewCustomerEvent() throws ExecutionException, InterruptedException, JsonProcessingException {
        String json = "{\"customer_event_id\":0,\"customer_event_type\":\"NEW\",\"customer_data\":{\"create_date\":\"2022-05-16T18:44:08.279Z\",\"customer_id\":2,\"email\":\"JOSEPEREZ@GMAIL.COM\",\"mobile_number\":\"1100009999\",\"name\":\"JOSEPEREZ\"}}";
        CustomerEvent customerEvent = objectMapper.readValue(json,CustomerEvent.class);

        kafkaTemplate.sendDefault(customerEvent.getCustomerEventId(),json).get();

        CountDownLatch latch = new CountDownLatch(1);
        latch.await(5, TimeUnit.SECONDS);

        verify(kafkaMQConsumerSpy, times(10)).onMessage(isA(ConsumerRecord.class));
    }
}
