package com.wise.studentdelivery.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/*
TODO: add profile image
TODO: add id card image
TODO: add student id number image
 */
@Data
@Document
public class User {
    // we use @Indexed(unique = true) for unique number to avoid duplicated in database
    @Id
    private String ID;
    private String firstName;
    private String lastName;
    private Gender gender;

    @Indexed(unique = true)
    private String emile;
    private String uniName;

    @Indexed(unique = true)
    private int phoneNumber;

    @Indexed(unique = true)
    private Long studentNumber;
    private int graduateYear;
    private Address address;
    private Car haveCar;
    private LocalDateTime createdTime;

    public User(String firstName, String lastName,
                Gender gender, String emile,
                String uniName,
                int phoneNumber,
                Long studentNumber,
                int graduateYear,
                Address address,
                Car haveCar,
                LocalDateTime createdTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emile = emile;
        this.uniName = uniName;
        this.phoneNumber = phoneNumber;
        this.studentNumber = studentNumber;
        this.graduateYear = graduateYear;
        this.address = address;
        this.haveCar = haveCar;
        this.createdTime = createdTime;
    }
}
