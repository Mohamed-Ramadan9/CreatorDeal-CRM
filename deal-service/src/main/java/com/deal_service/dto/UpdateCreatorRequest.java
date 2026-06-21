package com.deal_service.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCreatorRequest {

    private String displayName;

    private String country;

    private String mainPlatform;

    private String instagramHandle;

    private String tiktokHandle;

    private String youtubeHandle;

    @PositiveOrZero
    private Long followersCount;
}