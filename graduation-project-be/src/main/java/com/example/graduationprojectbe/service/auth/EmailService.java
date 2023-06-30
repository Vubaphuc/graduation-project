package com.example.graduationprojectbe.service.auth;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String email, String subject, String text) {

        // tạo nội dung gửi
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        // gửi mail
        javaMailSender.send(message);

    }

    public void sendPDFMail (String email, String subject, String text, byte[] pdfData) {
        // tạo nội dung gửi
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text);
            // đính kèm tập PDF
            ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfData,"application/pdf");
            helper.addAttachment("bill.pdf", dataSource);

            // gửi mail
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
