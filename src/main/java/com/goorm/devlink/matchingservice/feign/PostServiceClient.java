package com.goorm.devlink.matchingservice.feign;

import com.goorm.devlink.matchingservice.vo.request.PostMatchingRequest;
import com.goorm.devlink.matchingservice.vo.response.PostMatchingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "post-service")
public interface PostServiceClient {

    @GetMapping("/api/post/match")
    public ResponseEntity<List<PostMatchingResponse>> getPostMatchingData(@RequestBody PostMatchingRequest postMatchingRequest);

}
