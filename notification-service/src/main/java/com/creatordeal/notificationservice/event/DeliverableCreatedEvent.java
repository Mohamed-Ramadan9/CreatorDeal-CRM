package com.creatordeal.notificationservice.event;

import java.time.LocalDate;

public record DeliverableCreatedEvent(
        Long deliverableId,
        Long dealId,
        Long creatorId,
        LocalDate dueDate,
        String type
) {
}
