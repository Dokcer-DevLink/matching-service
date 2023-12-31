package com.goorm.devlink.matchingservice.service;

import com.goorm.devlink.matchingservice.dto.Address;
import com.goorm.devlink.matchingservice.vo.response.SearchPlaceResponse;

import java.util.List;

public interface MatchingService {


    List<SearchPlaceResponse> searchPlaceList(String place);

    Address searchAddress(String place);
}
