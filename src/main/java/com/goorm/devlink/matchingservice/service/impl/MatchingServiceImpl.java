package com.goorm.devlink.matchingservice.service.impl;

import com.goorm.devlink.matchingservice.dto.Address;
import com.goorm.devlink.matchingservice.service.MatchingService;
import com.goorm.devlink.matchingservice.util.KakaoAddressUtil;
import com.goorm.devlink.matchingservice.vo.response.SearchPlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final KakaoAddressUtil kakaoAddressUtil;

    @Override
    public List<SearchPlaceResponse> searchPlaceList(String place) {
        return kakaoAddressUtil.findAddressList(place);
    }

    @Override
    public Address searchAddress(String place) {
        return kakaoAddressUtil.getAddressInfo(place);
    }
}
