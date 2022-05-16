package com.tianshu.accounts.mapper;


import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto entityToDto(Customer customer);

    Customer dtoToEntity(CustomerDto customerDto);

    List<CustomerDto> entityListToDtoList(List<Customer> customers);
}
