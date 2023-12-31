package com.goorm.devlink.matchingservice.vo.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class EmptyScheduleRequest {

    List<String> userUuidList;
    private LocalDateTime startTime;
    private int unitTimeCount;

}
