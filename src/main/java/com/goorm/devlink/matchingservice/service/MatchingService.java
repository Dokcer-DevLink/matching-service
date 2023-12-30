package com.goorm.devlink.matchingservice.service;

import com.goorm.devlink.matchingservice.vo.response.SearchAddressResponse;

import java.util.List;

public interface MatchingService {


    List<SearchAddressResponse> getAddressList(String location);

}
