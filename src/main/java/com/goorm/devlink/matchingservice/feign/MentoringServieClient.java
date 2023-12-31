package com.goorm.devlink.matchingservice.feign;


import com.goorm.devlink.matchingservice.vo.request.MentoringApplyRequest;
import com.goorm.devlink.matchingservice.vo.response.ApplySimpleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

@FeignClient("mentoring-service")
public interface MentoringServieClient {

    @PostMapping("/api/mentoring/apply")
    public ResponseEntity<ApplySimpleResponse> applyMentoring(@RequestBody @Valid MentoringApplyRequest mentoringApplyRequest,
                                                               @RequestHeader("userUuid") String userUuid);
}
