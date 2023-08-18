package com.example.rentflatsharing.service;

public interface MailSender {

   void sendMail(String subject, String message, String sendTo);
}
