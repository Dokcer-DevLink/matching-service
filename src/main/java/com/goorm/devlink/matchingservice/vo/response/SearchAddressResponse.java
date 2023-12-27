package com.goorm.devlink.matchingservice.vo.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SearchAddressResponse {
    List<String> addressList;

    public static SearchAddressResponse getInstance(List<String> addressList){
        return SearchAddressResponse.builder()
                .addressList(addressList)
                .build();
    }
}
