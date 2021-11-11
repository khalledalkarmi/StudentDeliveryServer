package com.wise.studentdelivery.controller;

import com.wise.studentdelivery.model.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    TODO: add fetchByStudentNumber
    TODO: add fetchByPhoneNumber
    TODO: add fetchByEmail
    TODO: add fetchByCarNumber

 */
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> fetchAllStudent(){
        return userService.getAllUsers();
    }
}
