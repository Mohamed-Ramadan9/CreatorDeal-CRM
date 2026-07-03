package com.creatordeal.notificationservice.event;

import java.time.LocalDate;

public record DealCreatedEvent(
        Long dealId,
        Long creatorId,
        LocalDate dueDate,
        String title
) {
}
