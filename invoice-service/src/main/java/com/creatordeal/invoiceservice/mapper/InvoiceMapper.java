package com.creatordeal.invoiceservice.mapper;

import com.creatordeal.invoiceservice.dto.InvoiceResponse;
import com.creatordeal.invoiceservice.entity.Invoice;

public class InvoiceMapper {

    private InvoiceMapper() {
    }

    public static InvoiceResponse toResponse(Invoice invoice) {
        return InvoiceResponse.builder()
                .id(invoice.getId())
                .dealId(invoice.getDealId())
                .creatorId(invoice.getCreatorId())
                .invoiceNumber(invoice.getInvoiceNumber())
                .amount(invoice.getAmount())
                .currency(invoice.getCurrency())
                .status(invoice.getStatus())
                .issueDate(invoice.getIssueDate())
                .dueDate(invoice.getDueDate())
                .paidDate(invoice.getPaidDate())
                .createdAt(invoice.getCreatedAt())
                .build();
    }
}
