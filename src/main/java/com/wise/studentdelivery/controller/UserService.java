package com.wise.studentdelivery.controller;

import com.wise.studentdelivery.model.User;
import com.wise.studentdelivery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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

    public void sendMail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }


}
