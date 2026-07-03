package com.creatordeal.brandservice.mapper;

import com.creatordeal.brandservice.dto.BrandResponse;
import com.creatordeal.brandservice.entity.Brand;

public class BrandMapper {

    private BrandMapper() {}

    public static BrandResponse toResponse(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .creatorId(brand.getCreatorId())
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
