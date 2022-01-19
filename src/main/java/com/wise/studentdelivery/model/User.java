package com.wise.studentdelivery.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/*
TODO: delete car object and move it to Ride model
TODO: add id card image
TODO: add student id number image
 */
@Data
@Getter
@Setter
@Document
public class User {
    // we use @Indexed(unique = true) for unique number to avoid duplicated in database
    @Id
    private String ID;
    private String firstName;
    private String lastName;
    private String password;
    private Gender gender;

    @Indexed(unique = true)
    private String email;
    private String uniName;

    @Indexed(unique = true)
    private String  phoneNumber;

    @Indexed(unique = true)
    private String studentNumber;
    private int graduateYear;
    private Address address;
    private LocalDateTime createdTime;

    private Photo photo;
    private Ride ride;
    public User(String firstName, String lastName,
                Gender gender, String email,
                String uniName,
                String  phoneNumber,
                String studentNumber,
                int graduateYear,
                Address address,
                Car haveCar,
                LocalDateTime createdTime,
                String password
                ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.uniName = uniName;
        this.phoneNumber = phoneNumber;
        this.studentNumber = studentNumber;
        this.graduateYear = graduateYear;
        this.address = address;
        this.createdTime = createdTime;
        this.password=password;
    }
}
