package com.company.ecommerce.service;


import com.company.ecommerce.entity.Customer;
import com.company.ecommerce.repo.CustomerRepository;
import com.company.ecommerce.repo.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public Customer getCustomerByToken(String token){
        Customer customer = new Customer();
        Customer userId = tokenRepository.findCustomerIdByToken(token);
        Optional<Customer> givenCustomer = customerRepository.findById(userId.getId());
        if(givenCustomer.isPresent())
            return givenCustomer.get();
        return customer;
    }
}
