package com.tianshu.customers.service;

import com.tianshu.customers.dao.CustomerRepository;
import com.tianshu.customers.dto.CustomerDto;
import com.tianshu.customers.entity.Customer;
import com.tianshu.customers.exception.CustomerException;
import com.tianshu.customers.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
            throw new CustomerException(String.format("The customer with email %s is already exists.",customerDto.getEmail()));
        }

        customerDto.setCreateDate(new Date());
        final Customer saved = customerRepository.save(customerMapper.dtoToEntity(customerDto));
        log.info("The customer:{} is saved with Id:{}",saved.getEmail(), saved.getId());

        return customerMapper.entityToDto(saved);
    }

    public CustomerDto updateCustomer(CustomerDto customerDto){
        Optional.ofNullable(customerRepository.findByEmail(customerDto.getEmail()))
                .orElseThrow(() -> new EntityNotFoundException("Customer not found by Id:" + customerDto.getEmail()));

        final Customer updated = customerRepository.save(customerMapper.dtoToEntity(customerDto));
        log.info("The customer is updated with Id:{}", updated.getId());

        return customerMapper.entityToDto(updated);
    }

}
