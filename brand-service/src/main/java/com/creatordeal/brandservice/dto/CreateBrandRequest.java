package com.creatordeal.brandservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBrandRequest {
    @NotNull
    private Long creatorId;

    @NotBlank
    private String brandName;

    private String contactName;
    private String contactEmail;
    private String instagramHandle;
    private String website;
    private String notes;
}
