package com.goorm.devlink.matchingservice.vo.request;

import com.goorm.devlink.matchingservice.dto.Address;
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
    private Address address;
    private PostType postType;
    private OnOffline onOffline;

    public static PostMatchingRequest getInstance(List<String> stacks, PostType postType,
                                                  Address address, OnOffline onOffline) {
        return PostMatchingRequest.builder()
                .stacks(stacks)
                .address(address)
                .postType(postType)
                .onOffline(onOffline)
                .build();
    }

}
