package com.example.rentflatsharing.service.impl;

import com.example.rentflatsharing.model.entity.User;
import com.example.rentflatsharing.service.MailSender;
import com.example.rentflatsharing.service.ScheduledService;
import com.example.rentflatsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.rentflatsharing.constants.ConstantProject.EMAIL_MESSAGE;
import static com.example.rentflatsharing.constants.ConstantProject.EMAIL_SUBJECT;


@Service
@RequiredArgsConstructor
public class ScheduledServiceImpl implements ScheduledService {
    private final UserService userService;
    private final MailSender mailSender;


    //  @Scheduled(fixedDelay = 20_000L)
    @Scheduled(cron = "0 0 10 * * *")
    public void sendDiscountOffer() {
        List<User> allUsers = userService.findAllUsers();
        allUsers.forEach(e -> mailSender.sendMail(EMAIL_SUBJECT, EMAIL_MESSAGE, e.getUsername()));
    }
}

