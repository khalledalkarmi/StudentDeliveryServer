package com.wise.studentdelivery.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private ZonedDateTime createdTime;

}
