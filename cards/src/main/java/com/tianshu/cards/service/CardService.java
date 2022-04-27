package com.tianshu.cards.service;

import com.tianshu.cards.dao.CardRepository;
import com.tianshu.cards.dto.CardDto;
import com.tianshu.cards.entity.Card;
import com.tianshu.cards.exception.CardNotFoundByCustomerIdException;
import com.tianshu.cards.util.EntityDtoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<CardDto> getCardsByCustomerId(Long id){
        List<Card> cards = cardRepository.findByCustomerId(id);

        if(CollectionUtils.isEmpty(cards)){
            throw new CardNotFoundByCustomerIdException(id);
        }

        List<CardDto> cardDtos = new ArrayList<>();
        cards.forEach(c -> cardDtos.add(EntityDtoUtil.entityToDto(c, CardDto.class)));

        return cardDtos;
    }
}
