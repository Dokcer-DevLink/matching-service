package com.goorm.devlink.matchingservice.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class MatchingResponse {

    String applyUuid;
    String targetUuid;
    String postUuid;

    public static MatchingResponse getInstance(String applyUuid, String targetUuid, String postUuid){
        return MatchingResponse.builder()
                .applyUuid(applyUuid)
                .targetUuid(targetUuid)
                .postUuid(postUuid)
                .build();
    }

}
