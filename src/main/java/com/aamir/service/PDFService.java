package com.aamir.service;

import com.aamir.entity.Customer;
import com.aamir.record.ResponseCustomer;

public interface PDFService {
    byte[] generatePdf(ResponseCustomer customer) throws Exception;
}
