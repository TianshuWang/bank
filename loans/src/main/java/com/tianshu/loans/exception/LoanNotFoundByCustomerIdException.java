package com.tianshu.loans.exception;

public class LoanNotFoundByCustomerIdException extends RuntimeException{
    public LoanNotFoundByCustomerIdException(Long customerID){
        super("Loans not found by Customer Id:" + customerID);
    }
}
