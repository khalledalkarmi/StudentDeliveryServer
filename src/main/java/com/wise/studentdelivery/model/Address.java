package com.wise.studentdelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class Address {
    private String country;
    private String city;
}
