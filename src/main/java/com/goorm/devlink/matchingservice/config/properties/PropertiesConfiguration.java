package com.goorm.devlink.matchingservice.config.properties;

import com.goorm.devlink.matchingservice.config.properties.vo.KakaoAddressVo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KakaoAddressProperties.class)
@RequiredArgsConstructor
public class PropertiesConfiguration {

    private final KakaoAddressProperties kakaoAddressProperties;

    @Bean
    public KakaoAddressVo kakaoAddressVo(){
        return new KakaoAddressVo(kakaoAddressProperties.getSecretKey(), kakaoAddressProperties.getUrl());
    }
}
