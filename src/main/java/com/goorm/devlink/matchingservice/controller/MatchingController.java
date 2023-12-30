package com.goorm.devlink.matchingservice.controller;


import com.goorm.devlink.matchingservice.service.MatchingService;
import com.goorm.devlink.matchingservice.vo.response.SearchAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    @GetMapping("/api/matching/search")
    public ResponseEntity<SearchAddressResponse> searchLocation(@RequestParam String location){
        if( location.isEmpty() ){ throw new NoSuchElementException("location은 필수값입니다."); }
        List<String> addressList = matchingService.getAddressList(location);
        return ResponseEntity.ok(SearchAddressResponse.getInstance(addressList));
    }






}
