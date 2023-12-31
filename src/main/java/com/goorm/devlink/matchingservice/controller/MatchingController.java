package com.goorm.devlink.matchingservice.controller;


import com.goorm.devlink.matchingservice.dto.Address;
import com.goorm.devlink.matchingservice.feign.MentoringServieClient;
import com.goorm.devlink.matchingservice.feign.PostServiceClient;
import com.goorm.devlink.matchingservice.feign.ProfileServiceClient;
import com.goorm.devlink.matchingservice.service.MatchingService;
import com.goorm.devlink.matchingservice.vo.request.EmptyScheduleRequest;
import com.goorm.devlink.matchingservice.vo.request.MatchingRequest;
import com.goorm.devlink.matchingservice.vo.request.MentoringApplyRequest;
import com.goorm.devlink.matchingservice.vo.request.PostMatchingRequest;
import com.goorm.devlink.matchingservice.vo.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;
    private final ProfileServiceClient profileServiceClient;
    private final PostServiceClient postServiceClient;
    private final MentoringServieClient mentoringServieClient;


    @GetMapping("/api/matching/search")
    public ResponseEntity<List<SearchPlaceResponse>> searchLocation(@RequestParam String place){
        if( place.isEmpty() ){ throw new NoSuchElementException("location은 필수값입니다."); }
        return ResponseEntity.ok(matchingService.searchPlaceList(place));
    }

    @GetMapping("/api/matching")
    public ResponseEntity<MatchingResponse> doAutoMatch(@RequestBody @Valid MatchingRequest matchingRequest,
                                                        @RequestHeader("userUuid") String userUuid){

        if(userUuid.isEmpty()) { throw new NoSuchElementException("userUuid는 필수값입니다."); }

        //1. Post에서 매칭에 적합한 게시글 정보 가져오기
        List<PostMatchingResponse> postResponse = getPostMatchingResponse(matchingRequest,userUuid);
       if(postResponse.size() == 0) {
           throw  new IllegalArgumentException("요청에 적합한 게시글이 존재하지 않습니다."); }

        //2. Profile에서 스케줄에 적합한 유저 정보 가져오기
        EmptyScheduleResponse scheduleResponse = getScheduleResponse(matchingRequest,getPostUserList(postResponse));
       if(scheduleResponse.getUserUuidList().size() == 0) {
           throw  new IllegalArgumentException("요청에 적합한 유저가 존재하지 않습니다."); }

        //3. 우선순위가 가장 높은 유저에게 Mentoring 신청 보내기
        String targetUuid = getTargetUuid(scheduleResponse);
        String postUuid = getPostUuid(postResponse,targetUuid);
        ApplySimpleResponse mentoringResponse = getMentoringResponse(postUuid,targetUuid,userUuid,matchingRequest);

        return ResponseEntity.ok(MatchingResponse.getInstance(mentoringResponse.getApplyUuid(),targetUuid,postUuid));
    }

    private List<PostMatchingResponse> getPostMatchingResponse(MatchingRequest matchingRequest, String userUuid){
        return postServiceClient.getPostMatchingData(
                PostMatchingRequest.getInstance(
                        getStacks(userUuid), getAddress(matchingRequest), matchingRequest)
                ).getBody();
    }

    private EmptyScheduleResponse getScheduleResponse(MatchingRequest matchingRequest, List<String> postUserList){
        return profileServiceClient.findEnableUser(
                EmptyScheduleRequest.getInstance(
                        postUserList, matchingRequest.getStartTime(), matchingRequest.getUnitTimeCount()
                )).getBody();
    }

    private ApplySimpleResponse getMentoringResponse(String postUuid,String targetUuid, String userUuid,
                                                     MatchingRequest matchingRequest){

        return mentoringServieClient.applyMentoring(
                MentoringApplyRequest.getInstance(postUuid, targetUuid, matchingRequest),
                userUuid
        ).getBody();
    }

    private List<String> getPostUserList(List<PostMatchingResponse> postResponse){
        return postResponse.stream().map(PostMatchingResponse::getUserUuid).collect(Collectors.toList());
    }

    private String getTargetUuid(EmptyScheduleResponse scheduleResponse){
        return scheduleResponse.getUserUuidList().get(0);
    }
    private List<String> getStacks(String userUuid){
        return  profileServiceClient.viewUserStackList(userUuid).getBody();
    }

    private Address getAddress(MatchingRequest matchingRequest){
        return ( matchingRequest.getMentoringPlace() != null && !matchingRequest.getMentoringPlace().isEmpty()) ?
                matchingService.searchAddress(matchingRequest.getMentoringPlace()) : null;
    }

    private String getPostUuid(List<PostMatchingResponse> postMatchingResponses, String targetUuid) {
        return postMatchingResponses.stream().filter(response -> response.getUserUuid().equals(targetUuid))
                .map(PostMatchingResponse::getPostUuid).findFirst().get();
    }



}
