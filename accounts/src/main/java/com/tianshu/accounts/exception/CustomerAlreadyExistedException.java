package com.tianshu.accounts.exception;

public class CustomerAlreadyExistedException extends RuntimeException{
    public CustomerAlreadyExistedException(String email){
        super("Customer is already existed with email:" + email);
    }
}
