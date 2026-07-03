package com.creatordeal.dealservice.repository;

import com.creatordeal.dealservice.entity.Deal;
import com.creatordeal.dealservice.enums.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {

    List<Deal> findByCreatorId(Long creatorId);

    List<Deal> findByBrandId(Long brandId);

    List<Deal> findByStatus(DealStatus status);

    List<Deal> findByCreatorIdAndStatus(Long creatorId, DealStatus status);
}
