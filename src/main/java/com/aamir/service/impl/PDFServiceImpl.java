package com.aamir.service.impl;

import com.aamir.entity.Customer;
import com.aamir.record.ResponseCustomer;
import com.aamir.service.PDFService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PDFServiceImpl implements PDFService {
    @Override
    public byte[] generatePdf(ResponseCustomer customer) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            //Add Customer details to PDF
            document.add(new Paragraph("Customer Details "));
            document.add(new Paragraph("Customer id = " + customer.customerId()));
            document.add(new Paragraph("Customer Name = " + customer.customerName()));
            document.add(new Paragraph("Customer Email = " + customer.email()));
            document.close();
        } catch (Exception e) {
           throw new Exception(e);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
