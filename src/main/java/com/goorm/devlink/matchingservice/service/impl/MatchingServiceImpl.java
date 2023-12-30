package com.goorm.devlink.matchingservice.service.impl;

import com.goorm.devlink.matchingservice.service.MatchingService;
import com.goorm.devlink.matchingservice.util.KakaoAddressUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final KakaoAddressUtil kakaoAddressUtil;

    @Override
    public List<String> getAddressList(String location) {
        return kakaoAddressUtil.findAddressList(location);
    }
}
