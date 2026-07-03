package com.creatordeal.dealservice.dto;

import com.creatordeal.dealservice.enums.DealStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealResponse {

    private Long id;

    private Long creatorId;

    private Long brandId;

    private String title;

    private String description;

    private DealStatus status;

    private BigDecimal amount;

    private String currency;

    private LocalDate startDate;

    private LocalDate dueDate;

    private LocalDateTime createdAt;
}
