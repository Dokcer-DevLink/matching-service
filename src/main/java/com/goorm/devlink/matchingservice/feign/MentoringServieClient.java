package com.goorm.devlink.matchingservice.feign;


import com.goorm.devlink.matchingservice.vo.request.MentoringApplyRequest;
import com.goorm.devlink.matchingservice.vo.response.ApplySimpleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@FeignClient(name = "mentoring-service")
public interface MentoringServieClient {

    @PostMapping("/api/mentoring/auto")
    public ResponseEntity<ApplySimpleResponse> applyMentoring(@SpringQueryMap MentoringApplyRequest mentoringApplyRequest,
                                                               @RequestParam("userUuid") String userUuid);
}
