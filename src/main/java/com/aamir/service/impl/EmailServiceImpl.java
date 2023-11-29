package com.aamir.service.impl;

import com.aamir.record.ResponseCustomer;
import com.aamir.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailOfPdfOrExcel(ResponseCustomer customer, byte[] pdfOrExcel, String format) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(customer.email());
            helper.setSubject("Customer Details " + format);
            helper.setText("Dear " + customer.customerName() + "\n\nPlease find attached Customer user details " + format + " .", true);
            ByteArrayResource pdfAttachment = new ByteArrayResource(pdfOrExcel);
            helper.addAttachment("Customer Details." + format, pdfAttachment);
            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
