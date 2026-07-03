package com.creatordeal.dealservice.dto;

import com.creatordeal.dealservice.enums.DealStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDealRequest {

    @NotNull
    private Long creatorId;

    @NotNull
    private Long brandId;

    @NotBlank
    private String title;

    private String description;

    private DealStatus status;

    @PositiveOrZero
    private BigDecimal amount;

    private String currency;

    private LocalDate startDate;

    private LocalDate dueDate;
}
