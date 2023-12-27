package com.goorm.devlink.matchingservice.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MatchingResponse {

    private String userUuid;

    public static MatchingResponse convert(String userUuid){
        return MatchingResponse.builder()
                .userUuid(userUuid)
                .build();
    }
}
