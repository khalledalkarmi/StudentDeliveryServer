package com.wise.studentdelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Photo {
    private String IDForImage;
    private Binary image;
}
