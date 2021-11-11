package com.wise.studentdelivery.controller;

import com.wise.studentdelivery.model.User;
import com.wise.studentdelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
