package com.tianshu.loans.service;

import com.tianshu.loans.dao.LoanRepository;
import com.tianshu.loans.dto.LoanDto;
import com.tianshu.loans.entity.Loan;
import com.tianshu.loans.exception.LoanNotFoundByCustomerIdException;
import com.tianshu.loans.util.EntityDtoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<LoanDto> getLoansByCustomerId(Long id){
        List<Loan> loans = loanRepository.findByCustomerId(id);

        if(CollectionUtils.isEmpty(loans)){
            throw new LoanNotFoundByCustomerIdException(id);
        }

        List<LoanDto> loanDtos = new ArrayList<>();
        loans.forEach(l -> loanDtos.add(EntityDtoUtil.entityToDto(l, LoanDto.class)));

        return loanDtos;
    }
}
