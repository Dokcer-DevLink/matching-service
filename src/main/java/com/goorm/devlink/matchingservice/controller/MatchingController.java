package com.goorm.devlink.matchingservice.controller;


import com.goorm.devlink.matchingservice.dto.Address;
import com.goorm.devlink.matchingservice.feign.PostServiceClient;
import com.goorm.devlink.matchingservice.feign.ProfileServiceClient;
import com.goorm.devlink.matchingservice.service.MatchingService;
import com.goorm.devlink.matchingservice.vo.MatchingType;
import com.goorm.devlink.matchingservice.vo.OnOffline;
import com.goorm.devlink.matchingservice.vo.PostType;
import com.goorm.devlink.matchingservice.vo.request.MatchingRequest;
import com.goorm.devlink.matchingservice.vo.request.PostMatchingRequest;
import com.goorm.devlink.matchingservice.vo.response.PostMatchingResponse;
import com.goorm.devlink.matchingservice.vo.response.SearchPlaceResponse;
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
    public ResponseEntity<List<SearchPlaceResponse>> searchLocation(@RequestParam String place){
        if( place.isEmpty() ){ throw new NoSuchElementException("location은 필수값입니다."); }
        return ResponseEntity.ok(matchingService.searchPlaceList(place));
    }

    @GetMapping("/api/matching")
    public ResponseEntity doAutoMatch(@RequestBody @Valid MatchingRequest matchingRequest, @RequestHeader("userUuid") String userUuid){
        if(userUuid.isEmpty()) { throw new NoSuchElementException("userUuid는 필수값입니다."); }
        List<PostMatchingResponse> postMatchingData =
                postServiceClient.getPostMatchingData(getPostMatchingRequest(userUuid, matchingRequest)).getBody();

        //3  Profile에서 필터링된 userUuid 리스트 가져오기

    }

    private PostMatchingRequest getPostMatchingRequest(String userUuid, MatchingRequest matchingRequest){
        return PostMatchingRequest.getInstance(
                getStacks(userUuid),getPostType(matchingRequest),
                getAddress(matchingRequest),getOnOffline(matchingRequest)
        );
    }
    private List<String> getStacks(String userUuid){
        return  profileServiceClient.viewUserStackList(userUuid).getBody();
    }
    private OnOffline getOnOffline(MatchingRequest matchingRequest){
        return ( matchingRequest.getMentoringPlace() != null && !matchingRequest.getMentoringPlace().isEmpty()) ?
                OnOffline.OFFLINE : OnOffline.ONLINE;
    }
    private Address getAddress(MatchingRequest matchingRequest){
        return ( matchingRequest.getMentoringPlace() != null && !matchingRequest.getMentoringPlace().isEmpty()) ?
                matchingService.searchAddress(matchingRequest.getMentoringPlace()) : null;
    }
    private PostType getPostType(MatchingRequest matchingRequest){
        return ( matchingRequest.getMatchingType().equals(MatchingType.MENTOR))? PostType.MENTEE : PostType.MENTOR;
    }










}
