package com.aamir.service;

import com.aamir.entity.Customer;
import com.aamir.record.ResponseCustomer;

public interface ExcelService {
    byte[] generateExcel(ResponseCustomer customer) throws Exception;
}
