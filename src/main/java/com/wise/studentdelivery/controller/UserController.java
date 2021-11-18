package com.wise.studentdelivery.controller;

import com.wise.studentdelivery.model.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @GetMapping
    public List<User> fetchAllStudent() {
        LOG.info("Getting all Users.");
        List<User> test = userService.getAllUsers();
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public Optional<User> fetchByStudentNumber(@PathVariable String id) {
        LOG.info("Getting Student by id number: {}", id);
        return userService.getUserByIdNumber(id);
    }

    @GetMapping("/email/{email}")
    public Optional<User> fetchByEmail(@PathVariable String email) {
        LOG.info("Getting Student by email: {} ", email);
        // return email;
        return userService.getUserByEmail(email);
    }

    @GetMapping("/uni/{uni}")
    public Optional<List<User>> fetchStudentByUni(@PathVariable String uni) {
        LOG.info("Getting Student by uni name: {}", uni);
        return userService.getUsersByUni(uni);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
        LOG.info("user add {}",user.getFirstName());
    }



}
