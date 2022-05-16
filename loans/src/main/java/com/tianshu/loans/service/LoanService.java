package com.tianshu.loans.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianshu.loans.config.LoanServiceConfig;
import com.tianshu.loans.dao.LoanRepository;
import com.tianshu.loans.dto.LoanDto;
import com.tianshu.loans.entity.Loan;
import com.tianshu.loans.entity.Properties;
import com.tianshu.loans.exception.LoanNotFoundByCustomerIdException;
import com.tianshu.loans.mapper.LoanMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanServiceConfig loanConfig;

    @Autowired
    private LoanMapper loanMapper;

    public List<LoanDto> getLoansByCustomerId(Long id){
        List<Loan> loans = loanRepository.findByCustomerId(id);

        if(CollectionUtils.isEmpty(loans)){
            throw new LoanNotFoundByCustomerIdException(id);
        }

        return loanMapper.entityListToDtoList(loans);
    }

    public String getPropertyDetails() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties(loanConfig.getMsg(),loanConfig.getBuildVersion()
                ,loanConfig.getMailDetails(),loanConfig.getActiveBranches());

        return objectMapper.writeValueAsString(properties);
    }
}
