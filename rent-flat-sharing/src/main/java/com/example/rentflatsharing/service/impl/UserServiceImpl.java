package com.example.rentflatsharing.service.impl;

import com.example.rentflatsharing.exception.UserNotFoundException;
import com.example.rentflatsharing.model.entity.User;
import com.example.rentflatsharing.repository.UserRepository;
import com.example.rentflatsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<User> findOptionalUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
