package com.creatordeal.notificationservice.event;

import java.time.LocalDate;

public record InvoiceCreatedEvent(
        Long invoiceId,
        Long dealId,
        Long creatorId,
        LocalDate dueDate,
        String invoiceNumber
) {
}
