package com.creatordeal.creatorservice.mapper;

import com.creatordeal.creatorservice.dto.CreatorResponse;
import com.creatordeal.creatorservice.entity.Creator;

public class CreatorMapper {

    private CreatorMapper() {
    }

    public static CreatorResponse toResponse(Creator creator) {
        return CreatorResponse.builder()
                .id(creator.getId())
                .userId(creator.getUserId())
                .displayName(creator.getDisplayName())
                .country(creator.getCountry())
                .mainPlatform(creator.getMainPlatform())
                .instagramHandle(creator.getInstagramHandle())
                .tiktokHandle(creator.getTiktokHandle())
                .youtubeHandle(creator.getYoutubeHandle())
                .followersCount(creator.getFollowersCount())
                .createdAt(creator.getCreatedAt())
                .build();
    }
}