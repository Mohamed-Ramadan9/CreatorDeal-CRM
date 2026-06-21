package com.deal_service.repository;

import com.deal_service.entity.DealDeliverable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealDeliverableRepository extends JpaRepository<DealDeliverable, Long> {

    List<DealDeliverable> findByDeal_Id(Long dealId);
}
