package com.creatordeal.dealservice.repository;

import com.creatordeal.dealservice.entity.DealDeliverable;
import com.creatordeal.dealservice.enums.DeliverableStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealDeliverableRepository extends JpaRepository<DealDeliverable, Long> {

    List<DealDeliverable> findByDeal_Id(Long dealId);

    List<DealDeliverable> findByDeal_IdAndStatus(Long dealId, DeliverableStatus status);
}
