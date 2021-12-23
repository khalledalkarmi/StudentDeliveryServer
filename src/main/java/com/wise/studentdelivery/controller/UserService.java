package com.wise.studentdelivery.controller;

import com.wise.studentdelivery.model.Photo;
import com.wise.studentdelivery.model.Ride;
import com.wise.studentdelivery.model.User;
import com.wise.studentdelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    private JavaMailSender mailSender;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> getUserByIdNumber(String id) {
        return userRepository.findUserByStudentNumber(id);
    }

    public Optional<List<User>> getUsersByUni(String uni) {
        return userRepository.findUsersByUniName(uni);
    }

    public boolean updatePassword(String email, String newPassword) {
        var user = getUserByEmail(email);
        if (user.isPresent()) {
            User updateUser = user.get();
            updateUser.setPassword(newPassword);
            userRepository.save(updateUser);
            LOG.info("{}", updateUser.getFirstName());
            LOG.info("user password updated {}", updateUser.getFirstName());
            return true;
        }
        return false;
    }

    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Ride getRideByEmail(String email) {
        var user = getUserByEmail(email);
        if (user.isPresent()) {
            LOG.info("get Ride by email for user: {} {}", user.get().getFirstName(),user.get().getLastName());
            return user.get().getRide();
        }
        return null;
    }

    public List<Ride> getAllRide() {
        var allUser = getAllUsers();
        List<Ride> allRide = new ArrayList<>();
        for (User u : allUser) {
            if (u.getRide() != null)
                allRide.add(u.getRide());
        }
        return allRide;
    }


    public void addRide(String email, Ride ride) {
        var user = getUserByEmail(email);
        if (user.isPresent()) {
            User updateUserRide = user.get();
            ride.setEmail(email);
            ride.setFirstName(user.get().getFirstName());
            updateUserRide.setRide(ride);
            userRepository.save(updateUserRide);
            LOG.info("ride added by email for user: {}", user.get().getEmail());
        }
    }

    public String getUserPasswordByEmail(String email) {
        var user = getUserByEmail(email);
        return user.map(User::getPassword).orElse(null);
    }

    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void addImage(String email, MultipartFile file) throws IOException {
        var user = getUserByEmail(email);
        if (user.isPresent()) {
            Photo photo = new Photo(user.get().getID(), new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            user.get().setPhoto(photo);
            userRepository.save(user.get());
            LOG.info("Photo added for user{} ", user.get().getFirstName());
        }
    }


}
