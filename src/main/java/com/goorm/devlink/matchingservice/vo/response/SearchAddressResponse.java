package com.goorm.devlink.matchingservice.vo.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SearchAddressResponse {
    String place;
    String address;

    public static SearchAddressResponse getInstance(String place,String address ){
        return SearchAddressResponse.builder()
                .place(place)
                .address(address)
                .build();
    }
}
