package com.wise.studentdelivery.repository;

import com.wise.studentdelivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
//TODO: add findUserByCarNumber
public interface UserRepository extends MongoRepository <User,String >{

    String findUserByEmile(String email);
    Optional<Integer> findUserByPhoneNumber(int phoneNumber);
    // to find student by id number
    String findUserByStudentNumber(String studentNumber);
    Optional<List<User>> findUsersByUniName(String uniName);
}
