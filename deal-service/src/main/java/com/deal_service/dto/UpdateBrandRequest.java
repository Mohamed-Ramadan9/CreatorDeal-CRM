package com.deal_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBrandRequest {
    private String brandName;
    private String contactName;
    private String contactEmail;
    private String instagramHandle;
    private String website;
    private String notes;
}
