package com.goorm.devlink.matchingservice.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goorm.devlink.matchingservice.vo.MatchingType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
public class MatchingRequest {

    @NotNull(message = "멘토링 시간을 입력해주세요.")
    private LocalDateTime startTime;
    @NotNull(message = "러닝타임을 입력해주세요.")
    private Integer unitTimeCount;

    @NotNull(message = "멘토링 대상의 유형을 선택해주세요 ( MENTOR/MENTEE )")
    @JsonProperty("type")
    private MatchingType matchingType;

    @JsonProperty("place")
    private String mentoringPlace;




}
