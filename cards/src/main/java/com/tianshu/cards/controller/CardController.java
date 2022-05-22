package com.tianshu.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tianshu.cards.dto.CardDto;
import com.tianshu.cards.dto.CustomerDto;
import com.tianshu.cards.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Cards Controller")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/cards/customer")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Customer's cards details")
    public List<CardDto> getCardsByCustomerId(@ApiParam("Customer") @RequestHeader("bank-correlation-id") String correlationId, @RequestBody CustomerDto dto){
        return cardService.getCardsByCustomerId(dto.getId());
    }

    @GetMapping("/cards/properties")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Cards's properties")
    public String getPropertyDetails() throws JsonProcessingException {
        return cardService.getPropertyDetails();
    }
}
