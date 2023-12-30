package com.goorm.devlink.matchingservice.vo.request;

import com.goorm.devlink.matchingservice.vo.MatchingType;
import com.goorm.devlink.matchingservice.vo.OnOffline;
import com.goorm.devlink.matchingservice.vo.PostType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostMatchingRequest {
    private List<String> stacks;
    private Double addressX;
    private Double addressY;
    private PostType postType;
    private OnOffline onOffline;

    public static PostMatchingRequest getInstance(MatchingRequest matchingRequest,List<String> stacks,OnOffline onOffline,Double addressX, Double addressY){
        return PostMatchingRequest.builder()
                .stacks(stacks)
                .addressX(addressX)
                .addressY(addressY)
                .postType(convertToPostType(matchingRequest.getMatchingType()))
                .onOffline(onOffline)
                .build();
    }

    // 멘토를 찾으면 멘티찾기 포스트를, 멘티를 찾으면 멘토찾기 포스트를
    public static PostType convertToPostType(MatchingType matchingType){
        return ( matchingType.equals(MatchingType.MENTOR))? PostType.MENTEE : PostType.MENTOR;
    }
}
