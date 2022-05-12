package com.tianshu.accounts.service;

import com.tianshu.accounts.dao.CustomerRepository;
import com.tianshu.accounts.dto.CustomerDto;
import com.tianshu.accounts.entity.Customer;
import com.tianshu.accounts.exception.CustomerAlreadyExistedException;
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

    public List<CustomerDto> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDto> customerDtos = new ArrayList<>();
        customers.forEach(c -> customerDtos.add(EntityDtoUtil.entityToDto(c, CustomerDto.class)));

        return customerDtos;
    }

    public CustomerDto getCustomerById(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found by Id:" + id));

        return EntityDtoUtil.entityToDto(customer, CustomerDto.class);
    }

    public CustomerDto createCustomer(CustomerDto customerDto){
        Customer customer = customerRepository.findByEmail(customerDto.getEmail());

        if(customer != null){
            throw new CustomerAlreadyExistedException(customerDto.getEmail());
        }

        final Customer saved = customerRepository.save(EntityDtoUtil.dtoToEntity(customerDto, Customer.class));

        return EntityDtoUtil.entityToDto(saved, CustomerDto.class);
    }

    public CustomerDto updateCustomer(CustomerDto customerDto){
        Optional.ofNullable(customerRepository.findByEmail(customerDto.getEmail()))
                .orElseThrow(() -> new EntityNotFoundException("Customer not found by Id:" + customerDto.getEmail()));

        final Customer saved = customerRepository.save(EntityDtoUtil.dtoToEntity(customerDto, Customer.class));

        return EntityDtoUtil.entityToDto(saved, CustomerDto.class);
    }

}
