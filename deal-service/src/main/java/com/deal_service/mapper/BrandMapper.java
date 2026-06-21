package com.deal_service.mapper;

import com.deal_service.dto.BrandResponse;
import com.deal_service.entity.Brand;

public class BrandMapper {

    private BrandMapper() {}

    public static BrandResponse toResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .creatorId(brand.getCreator().getId())
                .creatorName(brand.getCreator().getDisplayName())
                .brandName(brand.getBrandName())
                .contactName(brand.getContactName())
                .contactEmail(brand.getContactEmail())
                .instagramHandle(brand.getInstagramHandle())
                .website(brand.getWebsite())
                .notes(brand.getNotes())
                .createdAt(brand.getCreatedAt())
                .build();
    }
}