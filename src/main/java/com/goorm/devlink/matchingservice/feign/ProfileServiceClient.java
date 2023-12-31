package com.goorm.devlink.matchingservice.feign;

import com.goorm.devlink.matchingservice.vo.request.EmptyScheduleRequest;
import com.goorm.devlink.matchingservice.vo.response.EmptyScheduleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


//@FeignClient(name = "profile-service", configuration = FeignClientConfig.class)
@FeignClient(name = "profile-service")
public interface ProfileServiceClient {

    @GetMapping("/api/profile/stacks")
    public ResponseEntity<List<String>> viewUserStackList(@RequestParam("userUuid") String userUuid);

    @PostMapping("/api/profile/enableUsers")
    public ResponseEntity<EmptyScheduleResponse> findEnableUser(@RequestBody EmptyScheduleRequest emptyScheduleRequest);

}
