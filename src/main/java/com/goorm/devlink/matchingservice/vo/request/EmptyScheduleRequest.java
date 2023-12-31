package com.goorm.devlink.matchingservice.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class EmptyScheduleRequest {

    List<String> userUuidList;
    private LocalDateTime startTime;
    private Integer unitTimeCount;

    public static EmptyScheduleRequest getInstance(List<String> userUuidList, LocalDateTime startTime,int unitTimeCount){
        return EmptyScheduleRequest.builder()
                .userUuidList(userUuidList)
                .startTime(startTime)
                .unitTimeCount(unitTimeCount)
                .build();
    }

}
