package com.goorm.devlink.matchingservice.dto;

import com.goorm.devlink.matchingservice.vo.OnOffline;
import com.goorm.devlink.matchingservice.vo.PostType;
import com.goorm.devlink.matchingservice.vo.request.MatchingRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter @Setter
public class MatchingDto {
    private List<String> stacks;
    private Address address;
    private PostType postType;
    private OnOffline onOffline;

    public static MatchingRequest getInstance(List<String> stacks,MatchingRequest matchingRequest, Address address, OnOffline onOffline){
        return MatchingDto.builder()
                .stacks(stacks)
                .address(address)
                .postType()
    }
