package com.deal_service.dto;

import com.deal_service.enums.DealStatus;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDealRequest {

    private String title;

    private String description;

    private DealStatus status;

    @PositiveOrZero
    private BigDecimal amount;

    private String currency;

    private LocalDate startDate;

    private LocalDate dueDate;
}
