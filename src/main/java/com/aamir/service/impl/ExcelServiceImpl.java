package com.aamir.service.impl;

import com.aamir.entity.Customer;
import com.aamir.record.ResponseCustomer;
import com.aamir.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public byte[] generateExcel(ResponseCustomer customer) throws Exception {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        Sheet sheet = xssfWorkbook.createSheet("Customer Details");
        // creating header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Customer Id");
        headerRow.createCell(1).setCellValue("Customer Name");
        headerRow.createCell(2).setCellValue("Email");
        // creating data row
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(customer.customerId());
        dataRow.createCell(1).setCellValue(customer.customerName());
        dataRow.createCell(2).setCellValue(customer.email());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            xssfWorkbook.write(byteArrayOutputStream);
            xssfWorkbook.close();
        } catch (Exception e) {
            throw new Exception(e);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
