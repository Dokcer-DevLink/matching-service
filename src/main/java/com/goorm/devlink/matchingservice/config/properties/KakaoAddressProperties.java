package com.goorm.devlink.matchingservice.config.properties;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("spring.kakao.address")
@RequiredArgsConstructor
@ConstructorBinding
@Getter
public class KakaoAddressProperties {

    private final String secretKey;
    private final String url;
}
