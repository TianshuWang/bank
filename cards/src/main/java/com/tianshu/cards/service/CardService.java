package com.tianshu.cards.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.cards.config.CardServiceConfig;
import com.tianshu.cards.dao.CardRepository;
import com.tianshu.cards.dto.CardDto;
import com.tianshu.cards.entity.Card;
import com.tianshu.cards.entity.Properties;
import com.tianshu.cards.exception.CardNotFoundByCustomerIdException;
import com.tianshu.cards.util.EntityDtoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardService.class);

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardServiceConfig cardConfig;

    public List<CardDto> getCardsByCustomerId(Long id){
        logger.info("Get Customer's Cards Method Started");

        List<Card> cards = cardRepository.findByCustomerId(id);

        if(CollectionUtils.isEmpty(cards)){
            throw new CardNotFoundByCustomerIdException(id);
        }

        List<CardDto> cardDtos = new ArrayList<>();
        cards.forEach(c -> cardDtos.add(EntityDtoUtil.entityToDto(c, CardDto.class)));

        logger.info("Get Customer's Cards Method Ended");
        return cardDtos;
    }

    public String getPropertyDetails() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties(cardConfig.getMsg(),cardConfig.getBuildVersion()
                ,cardConfig.getMailDetails(),cardConfig.getActiveBranches());

        return objectMapper.writeValueAsString(properties);
    }
}
