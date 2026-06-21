package com.deal_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {
    private Long id;
    private Long creatorId;
    private String creatorName;
    private String brandName;
    private String contactName;
    private String contactEmail;
    private String instagramHandle;
    private String website;
    private String notes;
    private LocalDateTime createdAt;
}
