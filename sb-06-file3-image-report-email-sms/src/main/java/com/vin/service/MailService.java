package com.vin.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender sender;
    private final PdfService pdfService;

    public void send(String to) throws Exception {

        MimeMessage msg = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(to);
        helper.setSubject("User Report");
        helper.setText("Attached PDF");

        byte[] pdf = pdfService.generate();

        helper.addAttachment("report.pdf", new ByteArrayResource(pdf));

        sender.send(msg);
    }
}