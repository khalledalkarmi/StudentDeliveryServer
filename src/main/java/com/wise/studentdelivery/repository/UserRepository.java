package com.wise.studentdelivery.repository;

import com.wise.studentdelivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
//TODO: add findUserByCarNumber
public interface UserRepository extends MongoRepository <User,String >{

    Optional<User> findUserByEmile(String email);
    Optional<User> findUserByPhoneNumber(int phoneNumber);
    // to find student by id number
    Optional<User> findUserByStudentNumber(Long studentNumber);
    Optional<User> findUsersByUniName(String uniName);
}
