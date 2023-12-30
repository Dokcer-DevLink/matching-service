package com.goorm.devlink.matchingservice.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.goorm.devlink.matchingservice.config.properties.vo.KakaoAddressVo;
import com.goorm.devlink.matchingservice.dto.AddressDto;
import com.goorm.devlink.matchingservice.vo.response.SearchAddressResponse;
import io.netty.util.CharsetUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class KakaoAddressUtil {

    private final KakaoAddressVo kakaoAddressVo;
    private final RestTemplate restTemplate;

    public List<SearchAddressResponse> findAddressList(String location){
        // 에러처리 필요
        String response = restTemplate.exchange(request(location),String.class).getBody();
        return parseResponse(response);

    }
    
//    public AddressDto getAddressInfo(String location){
//        String response = restTemplate.exchange(request(location),String.class).getBody();
//
//    }

    private RequestEntity request(String location){
        return new RequestEntity(headers(), HttpMethod.GET,url(location));
    }
    private HttpHeaders headers(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",kakaoAddressVo.getSecretKey());
        return headers;
    }

    private URI url(String location){
        return UriComponentsBuilder.fromHttpUrl(kakaoAddressVo.getUrl())
                .queryParam("query",location)
                .encode(CharsetUtil.UTF_8)
                .build()
                .toUri();
    }
    private List<SearchAddressResponse> parseResponse(String response){
        return convertJsonArrayToList(getJsonArray(response));
    }

    private JsonArray getJsonArray(String response){
        JsonParser jsonParser = new JsonParser();
        JsonObject parsedResponse = (JsonObject) jsonParser.parse(response);
        return parsedResponse.get("documents").getAsJsonArray();
    }

    private List<SearchAddressResponse> convertJsonArrayToList(JsonArray jsonArray){
        List<SearchAddressResponse> result = new ArrayList<>();
        jsonArray.forEach(jsonElement -> {
            String place = getValue(jsonElement.getAsJsonObject(),"place_name");
            String address = getValue(jsonElement.getAsJsonObject(),"address_name");
            result.add(SearchAddressResponse.getInstance(place,address));
        });
        return result;
    }

    private String getValue(JsonObject jsonObject, String key){
        return  jsonObject.get(key).toString().replaceAll("\"","");
    }

//    private List<String> convertJsonArrayToList(JsonObject jsonObject){
//        JsonObject data = jsonObject.get("address").getAsJsonObject();
//                    .get("address_name")
//                    .toString().replaceAll("\"","");
//            result.add(address);
//
//        return result;
//    }

}
