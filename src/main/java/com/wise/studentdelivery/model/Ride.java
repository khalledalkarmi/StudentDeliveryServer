package com.wise.studentdelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//TODO: move car object to this model
@Data
@Getter
@Setter
public class Ride {
    private String email;
    private String firstName;
    private String lastName;
    private Photo photo;
    private String goTime;
    private String comeBackTime;
    private String uniName;
    private String cityName;
    private String neighborhoodName;
    private String emptySeats;
    private String price;
    private String extraDetails;
    private String genderSpecific;
    private Car haveCar;
    private String isPrivate;

    public Ride(String goTime, String comeBackTime,
                String uniName, String cityName,
                String neighborhoodName, String emptySeats,
                String price, String extraDetails,
                String genderSpecific,
                String isPrivate,
                Car haveCar) {
        this.goTime = goTime;
        this.comeBackTime = comeBackTime;
        this.uniName = uniName;
        this.cityName = cityName;
        this.neighborhoodName = neighborhoodName;
        this.emptySeats = emptySeats;
        this.price = price;
        this.extraDetails = extraDetails;
        this.genderSpecific = genderSpecific;
        this.haveCar=haveCar;
        this.isPrivate = isPrivate;
    }

}
