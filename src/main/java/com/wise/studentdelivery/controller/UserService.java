package com.wise.studentdelivery.controller;

import com.wise.studentdelivery.model.User;
import com.wise.studentdelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByIdNumber(String  id){
        return userRepository.findUserByStudentNumber(id);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<List<User>> getUsersByUni(String uni){
        return userRepository.findUsersByUniName(uni);
    }

    public String addUser (User user){
        userRepository.save(user);
        return "user added";
    }

}
