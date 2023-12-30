package com.goorm.devlink.matchingservice.vo.request;

import com.goorm.devlink.matchingservice.vo.MatchingType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class MatchingRequest {

    private LocalDateTime startTime;
    private int unitTimeCount;
    private String mentoringPlace;
    private MatchingType matchingType;

}
