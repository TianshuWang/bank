package com.tianshu.accounts.service;

import com.tianshu.accounts.dao.CustomerRepository;
import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.entity.Customer;
import com.tianshu.accounts.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDto> customerDtos = new ArrayList<>();
        customers.forEach(c -> customerDtos.add(EntityDtoUtil.entityToDto(c, CustomerDto.class)));

        return customerDtos;
    }

    public CustomerDto getCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found by Id:" + id));

        return EntityDtoUtil.entityToDto(customer, CustomerDto.class);
    }
}
