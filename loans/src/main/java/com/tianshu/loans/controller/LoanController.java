package com.tianshu.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tianshu.loans.dto.CustomerDto;
import com.tianshu.loans.dto.LoanDto;
import com.tianshu.loans.service.LoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Loans Controller")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("loans/customer")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Customer's loans details")
    public List<LoanDto> getLoansByCustomerId(@ApiParam("Customer") @RequestHeader("bank-correlation-id") String correlationId, @RequestBody CustomerDto dto){
        return loanService.getLoansByCustomerId(dto.getId());
    }

    @GetMapping("loans/properties")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Loans properties")
    public String getPropertyDetails() throws JsonProcessingException {
        return loanService.getPropertyDetails();
    }
}
