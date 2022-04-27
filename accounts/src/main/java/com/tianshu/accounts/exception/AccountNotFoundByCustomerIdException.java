package com.tianshu.accounts.exception;

public class AccountNotFoundByCustomerIdException extends RuntimeException{
    public AccountNotFoundByCustomerIdException(Long customerID){
        super("Accounts not found by Customer Id:" + customerID);
    }
}
