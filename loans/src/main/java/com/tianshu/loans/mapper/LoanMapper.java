package com.tianshu.loans.mapper;

import com.tianshu.loans.dto.LoanDto;
import com.tianshu.loans.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    LoanMapper loanMapper = Mappers.getMapper(LoanMapper.class);

    Loan dtoToEntity(LoanDto loanDto);

    LoanDto entityToDto(Loan loan);

    List<LoanDto> entityListToDtoList(List<Loan> loans);
}
