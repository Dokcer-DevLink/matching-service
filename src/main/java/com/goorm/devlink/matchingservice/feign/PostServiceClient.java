package com.goorm.devlink.matchingservice.feign;

import com.goorm.devlink.matchingservice.vo.request.PostMatchingRequest;
import com.goorm.devlink.matchingservice.vo.response.PostMatchingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "post-service")
public interface PostServiceClient {

    //@GetMapping("/api/post/match")
    @GetMapping("/api/post/match")
//    @Headers(value = "Content-Type: application/json")
    public ResponseEntity<List<PostMatchingResponse>> getPostMatchingData(@SpringQueryMap PostMatchingRequest postMatchingRequest);



}
