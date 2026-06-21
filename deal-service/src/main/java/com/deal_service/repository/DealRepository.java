package com.deal_service.repository;

import com.deal_service.entity.Deal;
import com.deal_service.enums.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {

    List<Deal> findByCreator_Id(Long creatorId);

    List<Deal> findByBrand_Id(Long brandId);

    List<Deal> findByStatus(DealStatus status);

    List<Deal> findByCreator_IdAndStatus(Long creatorId, DealStatus status);
}