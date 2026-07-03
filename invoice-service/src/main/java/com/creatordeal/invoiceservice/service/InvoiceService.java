package com.creatordeal.invoiceservice.service;

import com.creatordeal.invoiceservice.dto.CreateInvoiceRequest;
import com.creatordeal.invoiceservice.dto.InvoiceResponse;
import com.creatordeal.invoiceservice.dto.UpdateInvoiceRequest;
import com.creatordeal.invoiceservice.enums.InvoiceStatus;

import java.util.List;

public interface InvoiceService {

    InvoiceResponse createInvoice(CreateInvoiceRequest request);

    InvoiceResponse getInvoiceById(Long id);

    InvoiceResponse getInvoiceByDeal(Long dealId);

    List<InvoiceResponse> getInvoicesByCreator(Long creatorId);

    List<InvoiceResponse> getInvoicesByCreatorAndStatus(Long creatorId, InvoiceStatus status);

    InvoiceResponse updateInvoice(Long id, UpdateInvoiceRequest request);

    InvoiceResponse markInvoiceAsPaid(Long id);

    void deleteInvoice(Long id);
}
