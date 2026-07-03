package com.creatordeal.invoiceservice.dto;

import com.creatordeal.invoiceservice.enums.InvoiceStatus;
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
public class CreateInvoiceRequest {

    @NotNull
    private Long dealId;

    @NotNull
    private Long creatorId;

    private String invoiceNumber;

    @NotNull
    @PositiveOrZero
    private BigDecimal amount;

    @NotNull
    private String currency;

    private InvoiceStatus status;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate paidDate;
}