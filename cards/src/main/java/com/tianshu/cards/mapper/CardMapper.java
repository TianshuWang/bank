package com.tianshu.cards.mapper;

import com.tianshu.cards.dto.CardDto;
import com.tianshu.cards.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card dtoToEntity(CardDto cardDto);

    CardDto entityToDto(Card card);

    List<CardDto> entityListToDtoList(List<Card> cards);
}
