package com.tianshu.cards.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.cards.config.CardServiceConfig;
import com.tianshu.cards.dto.CardDto;
import com.tianshu.cards.dto.CustomerDto;
import com.tianshu.cards.entity.Properties;
import com.tianshu.cards.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cards")
@Api(tags = "Cards Controller")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private CardServiceConfig cardConfig;

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Customer's cards details")
    public List<CardDto> getLoansByCustomerId(@ApiParam("Customer") @RequestBody CustomerDto dto){
        return cardService.getCardsByCustomerId(dto.getId());
    }

    @GetMapping("properties")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Cards's properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties(cardConfig.getMsg(),cardConfig.getBuildVersion()
                ,cardConfig.getMailDetails(),cardConfig.getActiveBranches());

        return objectMapper.writeValueAsString(properties);
    }
}
