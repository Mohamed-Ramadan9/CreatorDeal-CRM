package com.creatordeal.invoiceservice.service;

import com.creatordeal.invoiceservice.client.CreatorClient;
import com.creatordeal.invoiceservice.client.DealClient;
import com.creatordeal.invoiceservice.dto.CreateInvoiceRequest;
import com.creatordeal.invoiceservice.dto.InvoiceResponse;
import com.creatordeal.invoiceservice.dto.UpdateInvoiceRequest;
import com.creatordeal.invoiceservice.dto.external.DealResponse;
import com.creatordeal.invoiceservice.entity.Invoice;
import com.creatordeal.invoiceservice.enums.InvoiceStatus;
import com.creatordeal.invoiceservice.event.InvoiceCreatedEvent;
import com.creatordeal.invoiceservice.exception.DuplicateResourceException;
import com.creatordeal.invoiceservice.exception.ResourceNotFoundException;
import com.creatordeal.invoiceservice.mapper.InvoiceMapper;
import com.creatordeal.invoiceservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final StreamBridge streamBridge;
    private final CreatorClient creatorClient;
    private final DealClient dealClient;

    @Override
    public InvoiceResponse createInvoice(CreateInvoiceRequest request) {
        creatorClient.getCreatorById(request.getCreatorId());

        DealResponse dealResponse = dealClient.getDealById(request.getDealId());

        if (!dealResponse.creatorId().equals(request.getCreatorId())) {
            throw new IllegalArgumentException("Invoice creatorId must match the deal creatorId");
        }

        invoiceRepository.findByDealId(request.getDealId())
                .ifPresent(invoice -> {
                    throw new DuplicateResourceException("Invoice already exists for deal id: " + request.getDealId());
                });

        Invoice invoice = Invoice.builder()
                .dealId(request.getDealId())
                .creatorId(request.getCreatorId())
                .invoiceNumber(request.getInvoiceNumber())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .status(request.getStatus() != null ? request.getStatus() : InvoiceStatus.DRAFT)
                .issueDate(request.getIssueDate())
                .dueDate(request.getDueDate())
                .paidDate(request.getPaidDate())
                .build();

        Invoice savedInvoice = invoiceRepository.save(invoice);

        streamBridge.send("invoiceCreated-out-0", new InvoiceCreatedEvent(
                savedInvoice.getId(),
                savedInvoice.getDealId(),
                savedInvoice.getCreatorId(),
                savedInvoice.getDueDate(),
                savedInvoice.getInvoiceNumber()
        ));

        return InvoiceMapper.toResponse(savedInvoice);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponse getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));

        return InvoiceMapper.toResponse(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public InvoiceResponse getInvoiceByDeal(Long dealId) {
        Invoice invoice = invoiceRepository.findByDealId(dealId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found for deal id: " + dealId));

        return InvoiceMapper.toResponse(invoice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponse> getInvoicesByCreator(Long creatorId) {
        return invoiceRepository.findByCreatorId(creatorId)
                .stream()
                .map(InvoiceMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponse> getInvoicesByCreatorAndStatus(Long creatorId, InvoiceStatus status) {
        return invoiceRepository.findByCreatorIdAndStatus(creatorId, status)
                .stream()
                .map(InvoiceMapper::toResponse)
                .toList();
    }

    @Override
    public InvoiceResponse updateInvoice(Long id, UpdateInvoiceRequest request) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));

        if (request.getInvoiceNumber() != null) invoice.setInvoiceNumber(request.getInvoiceNumber());
        if (request.getAmount() != null) invoice.setAmount(request.getAmount());
        if (request.getCurrency() != null) invoice.setCurrency(request.getCurrency());
        if (request.getStatus() != null) invoice.setStatus(request.getStatus());
        if (request.getIssueDate() != null) invoice.setIssueDate(request.getIssueDate());
        if (request.getDueDate() != null) invoice.setDueDate(request.getDueDate());
        if (request.getPaidDate() != null) invoice.setPaidDate(request.getPaidDate());

        return InvoiceMapper.toResponse(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceResponse markInvoiceAsPaid(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));

        invoice.setStatus(InvoiceStatus.PAID);
        invoice.setPaidDate(LocalDate.now());

        return InvoiceMapper.toResponse(invoiceRepository.save(invoice));
    }

    @Override
    public void deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));

        invoiceRepository.delete(invoice);
    }
}
