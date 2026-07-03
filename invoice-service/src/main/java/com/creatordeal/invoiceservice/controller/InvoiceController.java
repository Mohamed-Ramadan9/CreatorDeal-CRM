package com.creatordeal.invoiceservice.controller;

import com.creatordeal.invoiceservice.dto.CreateInvoiceRequest;
import com.creatordeal.invoiceservice.dto.InvoiceResponse;
import com.creatordeal.invoiceservice.dto.UpdateInvoiceRequest;
import com.creatordeal.invoiceservice.enums.InvoiceStatus;
import com.creatordeal.invoiceservice.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceResponse createInvoice(@Valid @RequestBody CreateInvoiceRequest request) {
        return invoiceService.createInvoice(request);
    }

    @GetMapping("/{id}")
    public InvoiceResponse getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping("/deal/{dealId}")
    public InvoiceResponse getInvoiceByDeal(@PathVariable Long dealId) {
        return invoiceService.getInvoiceByDeal(dealId);
    }

    @GetMapping("/creator/{creatorId}")
    public List<InvoiceResponse> getInvoicesByCreator(@PathVariable Long creatorId) {
        return invoiceService.getInvoicesByCreator(creatorId);
    }

    @GetMapping("/creator/{creatorId}/status/{status}")
    public List<InvoiceResponse> getInvoicesByCreatorAndStatus(
            @PathVariable Long creatorId,
            @PathVariable InvoiceStatus status
    ) {
        return invoiceService.getInvoicesByCreatorAndStatus(creatorId, status);
    }

    @PutMapping("/{id}")
    public InvoiceResponse updateInvoice(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInvoiceRequest request
    ) {
        return invoiceService.updateInvoice(id, request);
    }

    @PatchMapping("/{id}/mark-paid")
    public InvoiceResponse markInvoiceAsPaid(@PathVariable Long id) {
        return invoiceService.markInvoiceAsPaid(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
    }
}