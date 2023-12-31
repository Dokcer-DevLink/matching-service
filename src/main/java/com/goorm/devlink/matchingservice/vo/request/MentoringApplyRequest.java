package com.goorm.devlink.matchingservice.vo.request;
import com.goorm.devlink.matchingservice.vo.MatchingType;
import com.goorm.devlink.matchingservice.vo.MentoringType;
import com.goorm.devlink.matchingservice.vo.OnOffline;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter @Setter
@Builder
public class MentoringApplyRequest {

    private String postUuid;
    private String targetUuid;
    private MentoringType targetType;
    private OnOffline onOffline;
    private LocalDateTime startTime;
    private Integer unitTimeCount;
    private String mentoringPlace;

    public static MentoringApplyRequest getInstance(String postUuid, String targetUuid, MatchingRequest matchingRequest){
        return MentoringApplyRequest.builder()
                .postUuid(postUuid)
                .targetUuid(targetUuid)
                .targetType(getMentoringType(matchingRequest))
                .onOffline(getOnOffline(matchingRequest))
                .startTime(matchingRequest.getStartTime())
                .unitTimeCount(matchingRequest.getUnitTimeCount())
                .mentoringPlace(matchingRequest.getMentoringPlace())
                .build();
    }

    private static MentoringType getMentoringType(MatchingRequest matchingRequest) {
        return ( matchingRequest.getMatchingType().equals(MatchingType.MENTOR))? MentoringType.MENTOR : MentoringType.MENTEE;

    }

    private static OnOffline getOnOffline(MatchingRequest matchingRequest){
        return ( matchingRequest.getMentoringPlace() != null && !matchingRequest.getMentoringPlace().isEmpty()) ?
                OnOffline.OFFLINE : OnOffline.ONLINE;
    }


}
