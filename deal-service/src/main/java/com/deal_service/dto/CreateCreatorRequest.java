package com.deal_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCreatorRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String displayName;

    private String country;

    private String mainPlatform;

    private String instagramHandle;

    private String tiktokHandle;

    private String youtubeHandle;

    @PositiveOrZero
    private Long followersCount;
}