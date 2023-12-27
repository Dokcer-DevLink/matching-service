package com.goorm.devlink.matchingservice;


import io.netty.util.CharsetUtil;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class KakaoApiTest {

    public static void main(String[] args) {
        String input = "구리";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","KakaoAK " + "" );
        UriComponents build = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/local/search/address")
                .queryParam("query",input)
                .encode(CharsetUtil.UTF_8)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.exchange(build.toUri(),HttpMethod.GET,new HttpEntity<>(headers),String.class);
        System.out.println(response.getBody());

    }
}
