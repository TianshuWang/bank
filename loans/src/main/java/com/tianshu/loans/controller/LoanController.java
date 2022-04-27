package com.tianshu.loans.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.loans.config.LoanServiceConfig;
import com.tianshu.loans.dto.CustomerDto;
import com.tianshu.loans.dto.LoanDto;
import com.tianshu.loans.entity.Properties;
import com.tianshu.loans.service.LoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("loans")
@Api(tags = "Loans Controller")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanServiceConfig loanConfig;

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Customer's loans details")
    public List<LoanDto> getLoansByCustomerId(@ApiParam("Customer") @RequestBody CustomerDto dto){
        return loanService.getLoansByCustomerId(dto.getId());
    }

    @GetMapping("properties")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Loans's properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties(loanConfig.getMsg(),loanConfig.getBuildVersion()
                ,loanConfig.getMailDetails(),loanConfig.getActiveBranches());

        return objectMapper.writeValueAsString(properties);
    }
}