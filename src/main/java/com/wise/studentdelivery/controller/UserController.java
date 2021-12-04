package com.wise.studentdelivery.controller;

import com.wise.studentdelivery.model.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/*
    TODO: add fetchByPhoneNumber
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
    public String fetchByEmail(@PathVariable String email) {
        LOG.info("Getting Student by email: {} ", email);
        // return email;
        userService.sendMail("khalled.95@gmail.com","first email"," empty ");
        var user = userService.getUserByEmail(email);
        return user.map(User::getEmail).orElse(null);
    }
    //change password
    @GetMapping("/upPass/{email}")
    public Boolean updatePassword(@PathVariable String email){
        LOG.info("update password Student by email: {} ", email);
        var user = userService.getUserByEmail(email);
        if (user.isPresent()){
           Random random = new Random();
            String emailBody= String.format("""
                    Hi %s\s

                    We received a request to reset the password for your account

                    To reset your password copy this pin code %04d""",user.get().getFirstName(),random.nextInt(10000));

            userService.sendMail(email,"Reset password",emailBody);
            return true;
        }
        return false;
    }

    @GetMapping("/uni/{uni}")
    public Optional<List<User>> fetchStudentByUni(@PathVariable String uni) {
        LOG.info("Getting Student by uni name: {}", uni);
        return userService.getUsersByUni(uni);
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public Optional<User> fetchUserByPhoneNumber(@PathVariable String phoneNumber){
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
        LOG.info("user add {}",user.getFirstName());
    }


}
