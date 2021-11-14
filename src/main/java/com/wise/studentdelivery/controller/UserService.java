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

    public String  getStudentByIdNumber(String  id){
        return userRepository.findUserByStudentNumber(id);
    }

    public String getStudentByEmail(String email){
        return userRepository.findUserByEmile(email);
    }

    public Optional<List<User>> getStudentsByUni(String uni){
        return userRepository.findUsersByUniName(uni);
    }
}
