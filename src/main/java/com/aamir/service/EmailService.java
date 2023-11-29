package com.aamir.service;

import com.aamir.record.ResponseCustomer;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    void sendEmailOfPdfOrExcel(ResponseCustomer customer, byte[] pdfOrExcel, String format);

}
