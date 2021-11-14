package com.wise.studentdelivery.repository;

import com.wise.studentdelivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
//TODO: add findUserByCarNumber
public interface UserRepository extends MongoRepository <User,String >{

    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPhoneNumber(int phoneNumber);
    // to find student by id number
    Optional<User> findUserByStudentNumber(String studentNumber);
    Optional<List<User>> findUsersByUniName(String uniName);
}
