package com.creatordeal.invoiceservice.dto;

import com.creatordeal.invoiceservice.enums.InvoiceStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceResponse {

    private Long id;

    private Long dealId;

    private Long creatorId;

    private String invoiceNumber;

    private BigDecimal amount;

    private String currency;

    private InvoiceStatus status;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate paidDate;

    private LocalDateTime createdAt;
}
