package com.example.rentflatsharing.service.impl;

import com.example.rentflatsharing.service.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.example.rentflatsharing.constants.ConstantProject.MAIL_USERNAME;

@Service
@RequiredArgsConstructor
public class MailSenderImp implements MailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(String subject, String message, String sendTo) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setFrom(MAIL_USERNAME);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }
}
