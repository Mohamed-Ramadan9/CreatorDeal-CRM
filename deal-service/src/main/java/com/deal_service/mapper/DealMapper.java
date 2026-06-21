package com.deal_service.mapper;

import com.deal_service.dto.DealResponse;
import com.deal_service.entity.Deal;

public class DealMapper {

    private DealMapper() {
    }

    public static DealResponse toResponse(Deal deal) {
        return DealResponse.builder()
                .id(deal.getId())
                .creatorId(deal.getCreator().getId())
                .creatorName(deal.getCreator().getDisplayName())
                .brandId(deal.getBrand().getId())
                .brandName(deal.getBrand().getBrandName())
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