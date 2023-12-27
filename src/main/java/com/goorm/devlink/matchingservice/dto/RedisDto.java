package com.goorm.devlink.matchingservice.dto;

import com.goorm.devlink.matchingservice.vo.MatchingType;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RedisDto implements Serializable {

    private String userUuid;
    private List<String> stacks;
    private MatchingType type;

    public static RedisDto getInstance(String userUuid, List<String> stacks, MatchingType type){
        return RedisDto.builder()
                .userUuid(userUuid)
                .stacks(stacks)
                .type(type)
                .build();
    }
}
