package com.creatordeal.invoiceservice.repository;

import com.creatordeal.invoiceservice.entity.Invoice;
import com.creatordeal.invoiceservice.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCreatorId(Long creatorId);

    List<Invoice> findByStatus(InvoiceStatus status);

    Optional<Invoice> findByDealId(Long dealId);

    List<Invoice> findByCreatorIdAndStatus(Long creatorId, InvoiceStatus status);
}
