package com.goorm.devlink.matchingservice.vo.request;

import com.goorm.devlink.matchingservice.dto.Address;
import com.goorm.devlink.matchingservice.vo.MatchingType;
import com.goorm.devlink.matchingservice.vo.OnOffline;
import com.goorm.devlink.matchingservice.vo.PostType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class PostMatchingRequest implements Serializable {
    private List<String> stacks;
    private Address address;
    private PostType postType;
    private OnOffline onOffline;

    public static PostMatchingRequest getInstance(List<String> stacks,
                                                  Address address, MatchingRequest matchingRequest ) {
        return PostMatchingRequest.builder()
                .stacks(stacks)
//                .address(address)
                .postType(getPostType(matchingRequest))
                .onOffline(getOnOffline(matchingRequest))
                .build();
    }

    private static PostType getPostType(MatchingRequest matchingRequest){
        return ( matchingRequest.getMatchingType().equals(MatchingType.MENTOR))? PostType.MENTEE : PostType.MENTOR;
    }

    private static OnOffline getOnOffline(MatchingRequest matchingRequest){
        return ( matchingRequest.getMentoringPlace() != null && !matchingRequest.getMentoringPlace().isEmpty()) ?
                OnOffline.OFFLINE : OnOffline.ONLINE;
    }


}
