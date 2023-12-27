package com.goorm.devlink.matchingservice.service;

import com.goorm.devlink.matchingservice.dto.RedisDto;
import com.goorm.devlink.matchingservice.vo.response.MatchingResponse;
import org.springframework.web.context.request.async.DeferredResult;

public interface MatchingService {

    public void waitMatchingInRedisQueue(RedisDto redisDto, DeferredResult<MatchingResponse> deferredResult);

}
