package com.creatordeal.creatorservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatorResponse {

    private Long id;
    private String userId;
    private String displayName;
    private String country;
    private String mainPlatform;
    private String instagramHandle;
    private String tiktokHandle;
    private String youtubeHandle;
    private Long followersCount;
    private LocalDateTime createdAt;
}