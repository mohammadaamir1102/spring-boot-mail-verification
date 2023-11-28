package com.aamir.service.impl;

import com.aamir.entity.Customer;
import com.aamir.record.RegisterCustomer;
import com.aamir.record.ResponseCustomer;
import com.aamir.repo.CustomerRepository;
import com.aamir.service.CustomerService;
import com.aamir.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final EmailService emailService;

    @Override
    public ResponseCustomer register(RegisterCustomer registerCustomer) {
        Customer existingCustomer = customerRepository.findByEmail(registerCustomer.email());
        if (existingCustomer != null && existingCustomer.isVerified()) {
            throw new RuntimeException("User Already Registered");
        }
        Customer customer = Customer.builder()
                .userName(registerCustomer.userName())
                .email(registerCustomer.email())
                .password(registerCustomer.password())
                .build();

        String otp = generateOTP();
        customer.setOtp(otp);

        Customer savedCustomer = customerRepository.save(customer);
        sendVerificationEmail(savedCustomer.getEmail(), otp);
        return new ResponseCustomer(customer.getUserName(), customer.getEmail());
    }

    @Override
    public void verify(String email, String otp) {

    }

    private String generateOTP() {
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    private void sendVerificationEmail(String email, String otp) {
        String subject = "Email verification";
        String body = "your verification otp is: " + otp;
        emailService.sendEmail(email, subject, body);
    }
}
