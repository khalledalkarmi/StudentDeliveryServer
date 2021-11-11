package com.wise.studentdelivery.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/*
TODO: add profile image
TODO: add id card image
 */
@Data
@Document
public class User {
    @Id
    private String ID;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String emile;
    private String uniName;
    private Long studentNumber;
    private int graduateYear;
    private Address address;
    private Car haveCar;
    private LocalDateTime createdTime;

    public User(String firstName, String lastName,
                Gender gender, String emile,
                String uniName, Long studentNumber,
                int graduateYear, Address address,
                Car haveCar, LocalDateTime createdTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emile = emile;
        this.uniName = uniName;
        this.studentNumber = studentNumber;
        this.graduateYear = graduateYear;
        this.address = address;
        this.haveCar = haveCar;
        this.createdTime = createdTime;
    }
}
