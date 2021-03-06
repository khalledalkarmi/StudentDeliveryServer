package com.wise.studentdelivery.controller;

import com.wise.studentdelivery.model.Ride;
import com.wise.studentdelivery.model.User;
import lombok.AllArgsConstructor;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
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
    public int requestPIN(@PathVariable String email) {
        var user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            Random random = new Random();
            int pin = random.nextInt(10000);
            String emailBody = String.format("""
                    Hi %s\s

                    We received a request to reset the password for your account

                    To reset your password copy this pin code %04d""", user.get().getFirstName(), pin);

            userService.sendMail(email, "Reset password", emailBody);
            LOG.info("Send PIN code to email: {} ", pin);
            return pin;
        }
        LOG.info("email not exist: {} ", email);
        return -1;
    }

    @GetMapping("/report/{report}")
    public String sendReport(@PathVariable String report){
        String ADMIN = "khalled.95@gmail.com";
        userService.sendMailReport(ADMIN,"report problem",report);
        return "true";
    }

    @GetMapping("/uni/{uni}")
    public Optional<List<User>> fetchStudentByUni(@PathVariable String uni) {
        LOG.info("Getting Student by uni name: {}", uni);
        return userService.getUsersByUni(uni);
    }

    @GetMapping("/phoneNumber/{phoneNumber}")
    public Optional<User> fetchUserByPhoneNumber(@PathVariable String phoneNumber) {
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        try {
            userService.addUser(user);
            LOG.info("user add {}", user.getFirstName());
            return "true";
        } catch (org.springframework.dao.DuplicateKeyException e) {
            if (Objects.requireNonNull(e.getMessage()).contains("email dup key")) {
                LOG.error("cannot add user because email dup");
                return "emailDup";
            }
            if (e.getMessage().contains("phoneNumber")) {
                LOG.error("cannot add user because phone number dup");
                return "phoneNumberDup";
            }
            if (e.getMessage().contains("studentNumber")) {
                LOG.error("cannot add user because student number dup");
                return "studentNumberDup";
            }
            System.out.println(e);
            return "false";
        }
    }



    @PostMapping("/updateuser")
    public String updateUser(@RequestBody User user){
        if (userService.updateUserInfo(user)) {
            userService.updateUserInfo(user);
            return "true";
        }
        return "false";
    }

    @PostMapping("/addride/{email}")
    public Boolean addRideByEmail(@RequestBody Ride ride, @PathVariable String email) {
        userService.addRide(email, ride);
        return true;
    }

    @GetMapping("/getride/{email}")
    public Ride getRideByEmail(@PathVariable String email) {
        LOG.info("get Ride for user {}", email);
        return userService.getRideByEmail(email);
    }

    @GetMapping("/getallride")
    public List<Ride> getAllRideBy() {
        LOG.info("get all Ride for users  ");
        return userService.getAllRide();
    }

    @PostMapping("/updatePassword/{email}/{newPassword}")
    public boolean updatePassword(@PathVariable String email, @PathVariable String newPassword) {
        LOG.info("password update for user {}", email);
        return userService.updatePassword(email, newPassword);
    }

    @GetMapping("/getpassword/{email}")
    public String getUserPassword(@PathVariable String email) {
        LOG.info("get user password by email {}", email);
        return userService.getUserPasswordByEmail(email);
    }

    @PostMapping("/addimage/{email}")
    public void addImage(@RequestParam MultipartFile image, @PathVariable String email) throws IOException {
        userService.addImage(email, image);
    }

    @GetMapping("/getimage/{email}")
    public Binary getImage(@PathVariable String email) {
        var user = userService.getUserByEmail(email);
        user.ifPresent(value -> LOG.info("get image for user: {}", value.getFirstName()));
        return user.map(value -> value.getPhoto().getImage()).orElse(null);
    }

    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        var user = userService.getUserByEmail(email);
        if (user.isPresent()) {
            LOG.info("get user by email user {}", user.get().getEmail());
            return userService.getUserByEmail(email);
        }
        return Optional.empty();
    }

    @GetMapping("/getridebyuni/{uni}")
    public List<Ride> getRideByUni(@PathVariable String uni) {
        return userService.getRideByUni(uni);
    }

    @GetMapping("/getridebycityName/{city}")
    public List<Ride> getRideByCityName(@PathVariable String city) {
        return userService.getRideByCityName(city);
    }

    @GetMapping("/getridebyneighborhoodname/{neighborhoodName}")
    public List<Ride> getRideByNeighborhoodName(@PathVariable String neighborhoodName) {
        return userService.getRideByNeighborhoodName(neighborhoodName);
    }

    @GetMapping("/getridebygreatprice/{price}")
    public List<Ride> getRideByGreatPrice(@PathVariable String price) {
        return userService.getRideByGreatPrice(price);
    }

    @GetMapping("/getridebylowerprice/{price}")
    public List<Ride> getRideByLowePrice(@PathVariable String price) {
        return userService.getRideByLowerPrice(price);
    }

    @GetMapping("/getridebygenderspecific/{gender}")
    private List<Ride> getRideByGenderSpecific(@PathVariable String gender) {
        return userService.getRideByGenderSpecific(gender);
    }


}
