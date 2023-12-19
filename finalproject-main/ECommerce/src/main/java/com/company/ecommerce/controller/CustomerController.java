package com.company.ecommerce.controller;

import com.company.ecommerce.entity.Customer;
import com.company.ecommerce.service.CustomerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer",method = RequestMethod.GET)
//@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/get-by-token/{token}")
    public ResponseEntity<?> getCustomerByToken(@PathVariable("token") String token){
        Customer customer = customerService.getCustomerByToken(token);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
}


