package com.tianshu.loans.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.loans.config.LoanServiceConfig;
import com.tianshu.loans.dao.LoanRepository;
import com.tianshu.loans.dto.LoanDto;
import com.tianshu.loans.entity.Loan;
import com.tianshu.loans.entity.Properties;
import com.tianshu.loans.exception.LoanNotFoundByCustomerIdException;
import com.tianshu.loans.util.EntityDtoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanServiceConfig loanConfig;

    public List<LoanDto> getLoansByCustomerId(Long id){
        logger.info("Get Customer's Loans Method Started");

        List<Loan> loans = loanRepository.findByCustomerId(id);

        if(CollectionUtils.isEmpty(loans)){
            throw new LoanNotFoundByCustomerIdException(id);
        }

        List<LoanDto> loanDtos = new ArrayList<>();
        loans.forEach(l -> loanDtos.add(EntityDtoUtil.entityToDto(l, LoanDto.class)));

        logger.info("Get Customer's Loans Method Ended");

        return loanDtos;
    }

    public String getPropertyDetails() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties(loanConfig.getMsg(),loanConfig.getBuildVersion()
                ,loanConfig.getMailDetails(),loanConfig.getActiveBranches());

        return objectMapper.writeValueAsString(properties);
    }
}
