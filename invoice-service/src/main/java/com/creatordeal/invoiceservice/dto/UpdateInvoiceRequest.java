package com.creatordeal.invoiceservice.dto;

import com.creatordeal.invoiceservice.enums.InvoiceStatus;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInvoiceRequest {

    private String invoiceNumber;

    @PositiveOrZero
    private BigDecimal amount;

    private String currency;

    private InvoiceStatus status;

    private LocalDate issueDate;

    private LocalDate dueDate;

    private LocalDate paidDate;
}