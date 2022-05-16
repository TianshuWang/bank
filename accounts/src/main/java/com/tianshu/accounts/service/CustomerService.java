package com.tianshu.accounts.service;

import com.tianshu.accounts.dao.CustomerRepository;
import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.entity.Customer;
import com.tianshu.accounts.exception.CustomerAlreadyExistedException;
import com.tianshu.accounts.mapper.CustomerMapper;
import com.tianshu.accounts.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<CustomerDto> getAllCustomers(){
         return customerMapper.entityListToDtoList(customerRepository.findAll());
    }

    public CustomerDto getCustomerById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found by Id:" + id));

        return customerMapper.entityToDto(customer);
    }

    public CustomerDto createCustomer(CustomerDto customerDto){
        Customer customer = customerRepository.findByEmail(customerDto.getEmail());

        if(customer != null){
            throw new CustomerAlreadyExistedException(customerDto.getEmail());
        }

        final Customer saved = customerRepository.save(customerMapper.dtoToEntity(customerDto));

        return customerMapper.entityToDto(saved);
    }

    public CustomerDto updateCustomer(CustomerDto customerDto){
        Optional.ofNullable(customerRepository.findByEmail(customerDto.getEmail()))
                .orElseThrow(() -> new EntityNotFoundException("Customer not found by Id:" + customerDto.getEmail()));

        final Customer saved = customerRepository.save(customerMapper.dtoToEntity(customerDto));

        return customerMapper.entityToDto(saved);
    }

}
