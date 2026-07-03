package com.creatordeal.dealservice.mapper;

import com.creatordeal.dealservice.dto.DealResponse;
import com.creatordeal.dealservice.entity.Deal;

public class DealMapper {

    private DealMapper() {
    }

    public static DealResponse toResponse(Deal deal) {
        return DealResponse.builder()
                .id(deal.getId())
                .creatorId(deal.getCreatorId())
                .brandId(deal.getBrandId())
                .title(deal.getTitle())
                .description(deal.getDescription())
                .status(deal.getStatus())
                .amount(deal.getAmount())
                .currency(deal.getCurrency())
                .startDate(deal.getStartDate())
                .dueDate(deal.getDueDate())
                .createdAt(deal.getCreatedAt())
                .build();
    }
}
