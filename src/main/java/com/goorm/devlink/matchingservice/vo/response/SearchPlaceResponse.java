package com.goorm.devlink.matchingservice.vo.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchPlaceResponse {
    String place;
    String address;

    public static SearchPlaceResponse getInstance(String place, String address ){
        return SearchPlaceResponse.builder()
                .place(place)
                .address(address)
                .build();
    }
}
