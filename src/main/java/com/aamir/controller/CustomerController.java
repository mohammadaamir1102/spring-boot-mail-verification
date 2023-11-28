package com.aamir.controller;

import com.aamir.record.RegisterCustomer;
import com.aamir.record.ResponseCustomer;
import com.aamir.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<ResponseCustomer> register(@RequestBody RegisterCustomer registerCustomer){
        ResponseCustomer responseCustomer = customerService.register(registerCustomer);
        return new ResponseEntity<>(responseCustomer, HttpStatus.CREATED);
    }

}
