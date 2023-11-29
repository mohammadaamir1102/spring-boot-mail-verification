package com.aamir.controller;

import com.aamir.record.ResponseCustomer;
import com.aamir.service.CustomerService;
import com.aamir.service.EmailService;
import com.aamir.service.ExcelService;
import com.aamir.service.PDFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verify")
@RequiredArgsConstructor
public class EmailOTPVerificationController {

    private final CustomerService customerService;
    private final ExcelService excelService;
    private final PDFService pdfService;
    private final EmailService emailService;

    @PostMapping("/otpVerify")
    public ResponseEntity<?> verifyUser(@RequestParam String email, @RequestParam String otp, @RequestParam String excelOrPdf) {
        try {
            ResponseCustomer responseCustomer = customerService.verify(email, otp);
            if (excelOrPdf.equalsIgnoreCase("pdf")) {
                byte[] customerPdf = pdfService.generatePdf(responseCustomer);
                emailService.sendEmailOfPdfOrExcel(responseCustomer, customerPdf, excelOrPdf);
            } else {
                byte[] customerExcel = excelService.generateExcel(responseCustomer);
                excelOrPdf = "xls";
                emailService.sendEmailOfPdfOrExcel(responseCustomer, customerExcel, excelOrPdf);
            }
            return new ResponseEntity<>("User verified successfully and "
                    + excelOrPdf + " has been sent to mail.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
