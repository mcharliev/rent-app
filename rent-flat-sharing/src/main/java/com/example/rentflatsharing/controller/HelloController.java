package com.example.rentflatsharing.controller;


import com.example.rentflatsharing.security.UserEntityDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntityDetails userEntityDetails =(UserEntityDetails) authentication.getPrincipal();
        System.out.println(userEntityDetails.getUser());
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntityDetails userEntityDetails =(UserEntityDetails) authentication.getPrincipal();
        System.out.println(userEntityDetails.getUser());
        return "hello";
    }
}
