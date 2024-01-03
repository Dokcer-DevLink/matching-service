package com.goorm.devlink.matchingservice.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.goorm.devlink.matchingservice.config.properties.vo.KakaoAddressVo;
import com.goorm.devlink.matchingservice.dto.Address;
import com.goorm.devlink.matchingservice.vo.response.SearchPlaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class KakaoAddressUtil {

    private final KakaoAddressVo kakaoAddressVo;
    private final RestTemplate restTemplate;

    public List<SearchPlaceResponse> findAddressList(String place){
        // 에러처리 필요
        String response = restTemplate.exchange(request(place,kakaoAddressVo.getKeywordUrl()),String.class).getBody();
        return parseResponseForList(response);

    }
    
    public Address getAddressInfo(String address){
        String response = restTemplate.exchange(request(address,kakaoAddressVo.getAddressUrl()),String.class).getBody();
        return parseResponseForAddress(response);

    }

    private RequestEntity request(String location,String url){
        return new RequestEntity(headers(), HttpMethod.GET,url(location,url));
    }
    private HttpHeaders headers(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",kakaoAddressVo.getSecretKey());
        return headers;
    }

    private URI url(String location, String url){
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("query",location)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
    }
    private List<SearchPlaceResponse> parseResponseForList(String response){
        return convertJsonArrayToList(getJsonArray(response));
    }

    private Address parseResponseForAddress(String response){
        return convertJsonToAddress(getJsonObject(response));
    }

    private JsonArray getJsonArray(String response){
        JsonParser jsonParser = new JsonParser();
        JsonObject parsedResponse = (JsonObject) jsonParser.parse(response);
        return parsedResponse.get("documents").getAsJsonArray();
    }

    private JsonObject getJsonObject(String response){
        JsonParser jsonParser = new JsonParser();
        JsonObject parsedResponse = (JsonObject)jsonParser.parse(response);
        checkResponseValid(parsedResponse);
        return parsedResponse.get("documents").getAsJsonArray().get(0).getAsJsonObject();
    }

    private List<SearchPlaceResponse> convertJsonArrayToList(JsonArray jsonArray){
        List<SearchPlaceResponse> result = new ArrayList<>();
        jsonArray.forEach(jsonElement -> {
            String place = getValue(jsonElement.getAsJsonObject(),"place_name");
            String address = getValue(jsonElement.getAsJsonObject(),"address_name");
            result.add(SearchPlaceResponse.getInstance(place,address));
        });
        return result;
    }

    private Address convertJsonToAddress(JsonObject jsonObject){
        JsonObject addressJson = jsonObject.get("address").getAsJsonObject();
        Double addressX = Double.valueOf(getValue(addressJson,"x"));
        Double addressY = Double.valueOf(getValue(addressJson,"y"));
        String addressName = getValue(addressJson,"address_name");
        return Address.getInstance(addressName,addressX,addressY);
    }

    private String getValue(JsonObject jsonObject, String key){
        return  jsonObject.get(key).toString().replaceAll("\"","");
    }

    private void checkResponseValid(JsonObject parsedResponse) {
        if(parsedResponse.get("documents").getAsJsonArray().size() == 0){
            throw new IllegalArgumentException("입력하신 장소 데이터는 유효하지 않습니다. 장소 검색 기능을 활용하여 정확한 장소 데이터를 입력해주세요. ");
        }
    }
}
