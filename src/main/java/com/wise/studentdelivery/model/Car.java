package com.wise.studentdelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Getter
@Setter

@AllArgsConstructor
public class Car {
    private String carModel;
    private String carColor;

    @Indexed(unique = true)
    private String carNumber;
}
