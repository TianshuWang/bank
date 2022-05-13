package com.tianshu.accounts;

import com.tianshu.accounts.message.CustomerData;
import com.tianshu.accounts.message.CustomerEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerEventsIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postCustomerEvent(){
        //given
        CustomerData customerData = CustomerData.builder()
                .id(2L)
                .name("MARIA FLORES")
                .email("MARIAFLORES@GMAIL.COM")
                .mobileNumber("1123456789")
                .createDate(new Date())
                .build();


        CustomerEvent customerEvent = CustomerEvent.builder()
                .customerEventId(1)
                .customerData(customerData)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("content-type", MediaType.APPLICATION_JSON_VALUE.toString());
        HttpEntity<CustomerEvent> request = new HttpEntity<>(customerEvent,headers);

        //when
        ResponseEntity<CustomerEvent> response = restTemplate.exchange("/kafka/customers", HttpMethod.POST, request, CustomerEvent.class);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
