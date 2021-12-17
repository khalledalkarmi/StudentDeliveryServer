package com.wise.studentdelivery.controller;

import com.wise.studentdelivery.model.User;
import lombok.AllArgsConstructor;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        var user = userService.getUserByEmail(email);
        return user.map(User::getEmail).orElse(null);
    }
    //change password
    @GetMapping("/pin/{email}")
    public int requestPIN(@PathVariable String email){
        var user = userService.getUserByEmail(email);
        if (user.isPresent()){
           Random random = new Random();
           int pin = random.nextInt(10000);
            String emailBody= String.format("""
                    Hi %s\s

                    We received a request to reset the password for your account

                    To reset your password copy this pin code %04d""",user.get().getFirstName(),pin);

            userService.sendMail(email,"Reset password",emailBody);
            LOG.info("Send PIN code to email: {} ", email);
            return pin;
        }
        LOG.info("email not exist: {} ", email);
        return -1;
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

    @PostMapping("/updatePassword/{email}/{newPassword}")
    public boolean updatePassword(@PathVariable String email,@PathVariable String newPassword){
        LOG.info("password update for user {}", email);
        return userService.updatePassword(email,newPassword);
    }

    @GetMapping("/getpassword/{email}")
    public String getUserPassword(@PathVariable String email){
        LOG.info("get user password by email {}", email);
        return userService.getUserPasswordByEmail(email);
    }

    @PostMapping("/addimage/{email}")
    public void addImage(@RequestParam MultipartFile image,@PathVariable String email) throws IOException {
        userService.addImage(email,image);
    }

    @GetMapping("/getimage/{email}")
    public Binary getImage(@PathVariable String email){
        var user = userService.getUserByEmail(email);
        user.ifPresent(value -> LOG.info("get image for user: {}", value.getFirstName()));
        return user.map(value -> value.getPhoto().getImage()).orElse(null);
    }

    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email){
        LOG.info("image {}",userService.getUserByEmail(email).get().getPhoto());
        return userService.getUserByEmail(email);
    }
}
