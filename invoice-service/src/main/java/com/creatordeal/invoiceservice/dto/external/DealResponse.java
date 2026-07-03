package com.creatordeal.invoiceservice.dto.external;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DealResponse(
        Long id,
        Long creatorId,
        Long brandId,
        String title,
        String description,
        String status,
        BigDecimal amount,
        String currency,
        LocalDate startDate,
        LocalDate dueDate,
        LocalDateTime createdAt
) {
}