package com.example.rentflatsharing.service;

import com.example.rentflatsharing.model.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User getUser(Long id);


    Optional<User> findOptionalUserByUserName(String username);

    List<User> findAllUsers();
}
