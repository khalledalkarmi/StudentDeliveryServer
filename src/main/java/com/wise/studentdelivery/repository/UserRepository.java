package com.wise.studentdelivery.repository;

import com.wise.studentdelivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User,String>{

}
