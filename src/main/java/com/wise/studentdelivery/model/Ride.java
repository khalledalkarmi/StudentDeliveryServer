package com.wise.studentdelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Ride {
    private String goTime;
    private String comeBackTime;
    private String uniName;
    private String cityName;
    private String neighborhoodName;
    private String emptySeats;
    private String price;
    private String extraDetails;
    private boolean isPrivate;
}
