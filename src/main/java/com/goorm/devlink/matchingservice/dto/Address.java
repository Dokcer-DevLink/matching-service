package com.goorm.devlink.matchingservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Address implements Serializable {

    String addressName;
    Double addressX;
    Double addressY;

    public static Address getInstance(String addressName, Double addressX, Double addressY){
        return Address.builder()
                .addressName(addressName)
                .addressX(addressX)
                .addressY(addressY)
                .build();
    }

}
