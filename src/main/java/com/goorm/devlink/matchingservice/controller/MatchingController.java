package com.goorm.devlink.matchingservice.controller;


import com.goorm.devlink.matchingservice.feign.PostServiceClient;
import com.goorm.devlink.matchingservice.feign.ProfileServiceClient;
import com.goorm.devlink.matchingservice.service.MatchingService;
import com.goorm.devlink.matchingservice.vo.request.MatchingRequest;
import com.goorm.devlink.matchingservice.vo.request.PostMatchingRequest;
import com.goorm.devlink.matchingservice.vo.response.SearchAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;
    private final ProfileServiceClient profileServiceClient;
    private final PostServiceClient postServiceClient;


    @GetMapping("/api/matching/search")
    public ResponseEntity<List<SearchAddressResponse>> searchLocation(@RequestParam String location){
        if( location.isEmpty() ){ throw new NoSuchElementException("location은 필수값입니다."); }
        return ResponseEntity.ok(matchingService.getAddressList(location));
    }

//    @GetMapping("/api/matching")
//    public ResponseEntity doAutoMatch(@RequestBody @Valid MatchingRequest matchingRequest, @RequestHeader("userUuid") String userUuid){
//        if(userUuid.isEmpty()) { throw new NoSuchElementException("userUuid는 필수값입니다."); }
//
//        //1. 기술스택 가져오기
//        List<String> stacks = profileServiceClient.viewUserStackList(userUuid).getBody();
//
//        //2. 위치 경도 가져오기
//
//        //2. Post에서 UserUuid 리스트 가져오기
////        postServiceClient.getPostMatchingData(PostMatchingRequest.getInstance(matchingRequest,stacks,))
//
//        //3  Profile에서 필터링된 userUuid 리스트 가져오기
//
//    }










}
