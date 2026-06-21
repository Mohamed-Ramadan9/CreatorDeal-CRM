package com.deal_service.repository;

import com.deal_service.entity.Invoice;
import com.deal_service.enums.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByCreator_Id(Long creatorId);

    List<Invoice> findByStatus(InvoiceStatus status);
}