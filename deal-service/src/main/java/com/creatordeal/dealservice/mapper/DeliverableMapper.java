package com.creatordeal.dealservice.mapper;

import com.creatordeal.dealservice.dto.DeliverableResponse;
import com.creatordeal.dealservice.entity.DealDeliverable;

public class DeliverableMapper {

    private DeliverableMapper() {
    }

    public static DeliverableResponse toResponse(DealDeliverable deliverable) {
        return DeliverableResponse.builder()
                .id(deliverable.getId())
                .dealId(deliverable.getDeal().getId())
                .type(deliverable.getType())
                .platform(deliverable.getPlatform())
                .description(deliverable.getDescription())
                .dueDate(deliverable.getDueDate())
                .status(deliverable.getStatus())
                .postedUrl(deliverable.getPostedUrl())
                .createdAt(deliverable.getCreatedAt())
                .build();
    }
}
