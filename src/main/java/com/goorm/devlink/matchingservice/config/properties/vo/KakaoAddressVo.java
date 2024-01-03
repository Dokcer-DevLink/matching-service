package com.goorm.devlink.matchingservice.config.properties.vo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KakaoAddressVo {

    private final String secretKey;
    private final String keywordUrl;
    private final String addressUrl;

}
