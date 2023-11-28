package com.aamir.service;

import com.aamir.record.RegisterCustomer;
import com.aamir.record.ResponseCustomer;

public interface CustomerService {

    ResponseCustomer register(RegisterCustomer registerCustomer);
    void verify(String email, String otp);
}
