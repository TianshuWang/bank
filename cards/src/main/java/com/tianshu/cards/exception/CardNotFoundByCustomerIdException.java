package com.tianshu.cards.exception;

public class CardNotFoundByCustomerIdException extends RuntimeException{
    public CardNotFoundByCustomerIdException(Long customerID){
        super("Cards not found by Customer Id:" + customerID);
    }
}
