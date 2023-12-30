package com.goorm.devlink.matchingservice;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.util.CharsetUtil;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class KakaoApiTest {

    public static void main(String[] args) {
        String input = "가";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","KakaoAK " + "f734b3868de26edbf8f0b6918c5ff8d8" );
        UriComponents build = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/local/search/address")
                .queryParam("query",input)
                .encode(CharsetUtil.UTF_8)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity request = new RequestEntity(headers,HttpMethod.GET,build.toUri());
        String response = restTemplate.exchange(request,String.class).getBody();

        JsonParser jsonParser = new JsonParser();
        JsonObject parsedResponse = (JsonObject) jsonParser.parse(response);
        JsonArray documents = parsedResponse.get("documents").getAsJsonArray();
        documents.forEach(jsonElement -> {
            String address = jsonElement.getAsJsonObject().get("address").getAsJsonObject().get("address_name").toString().replaceAll("\"","");
            System.out.println(address);
        });


    }
}
