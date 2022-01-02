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

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
            logger.info("{}", updateUser.getFirstName());
            logger.info("user password updated {}", updateUser.getFirstName());
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
            logger.info("get Ride by email for user: {} {}", user.get().getFirstName(), user.get().getLastName());
            return user.get().getRide();
        }
        return null;
    }

    public List<Ride> getAllRide() {
        var allUser = getAllUsers();
        List<Ride> allRide = new ArrayList<>();
        for (User u : allUser) {
            if (u.getRide() != null) {
                allRide.add(u.getRide());
                logger.info("get image for users {}",u.getPhoto());
            }
        }
        return allRide;
    }

    public List<Ride> getRideByUni(String uni) {
        var allRide = getAllRide();
        List<Ride> rideByUni = new ArrayList<>();
        for (Ride r : allRide) {
            if (r.getUniName().equals(uni)) {
                rideByUni.add(r);
            }
        }
        return rideByUni;
    }

    public List<Ride> getRideByCityName(String city) {
        var allRide = getAllRide();
        List<Ride> rideByCityName = new ArrayList<>();
        for (Ride ride : allRide) {
            if (ride.getCityName().equals(city)) {
                rideByCityName.add(ride);
            }
        }
        return rideByCityName;
    }


    public List<Ride> getRideByNeighborhoodName(String neighborhoodName) {
        var allRide = getAllRide();
        List<Ride> rideByNeighborhoodName = new ArrayList<>();
        for (Ride ride : allRide) {
            if (ride.getNeighborhoodName().equals(neighborhoodName)) {
                rideByNeighborhoodName.add(ride);
            }
        }
        return rideByNeighborhoodName;
    }

    public List<Ride> getRideByGreatPrice(String price) {
        var allRide = getAllRide();
        int intPrice = Integer.parseInt(price);
        List<Ride> rideByGreatPrice = new ArrayList<>();
        for (Ride ride : allRide) {
            int priceToCompare = Integer.parseInt(ride.getPrice());
            if (intPrice > priceToCompare)
                rideByGreatPrice.add(ride);
        }
        return rideByGreatPrice;
    }

    public List<Ride> getRideByLowerPrice(String price) {
        var allRide = getAllRide();
        int intPrice = Integer.parseInt(price);
        List<Ride> rideByLowerPrice = new ArrayList<>();
        for (Ride ride : allRide) {
            int priceToCompare = Integer.parseInt(ride.getPrice());
            if (intPrice < priceToCompare)
                rideByLowerPrice.add(ride);
        }
        return rideByLowerPrice;
    }

    public List<Ride> getRideByGenderSpecific(String gender) {
        var allRide = getAllRide();
        List<Ride> rideByGenderSpecific = new ArrayList<>();
        for (Ride ride : allRide) {
            if (ride.getGenderSpecific().equals(gender))
                rideByGenderSpecific.add(ride);

        }
        return rideByGenderSpecific;
    }

    public void addRide(String email, Ride ride) {
        var user = getUserByEmail(email);
        if (user.isPresent()) {
            User updateUserRide = user.get();
            ride.setEmail(email);
            if (user.get().getPhoto() != null)
                ride.setPhoto(user.get().getPhoto());
            ride.setLastName(user.get().getLastName());
            ride.setFirstName(user.get().getFirstName());
            updateUserRide.setRide(ride);
            userRepository.save(updateUserRide);
            logger.info("ride added by email for user: {}", user.get().getEmail());
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
            logger.info("Photo added for user{} ", user.get().getFirstName());
        }
    }


}
