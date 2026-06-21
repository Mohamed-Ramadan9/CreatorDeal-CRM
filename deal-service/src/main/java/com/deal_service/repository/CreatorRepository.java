package com.deal_service.repository;

import com.deal_service.entity.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator, Long> {

    Optional<Creator> findByUserId(String userId);

    boolean existsByUserId(String userId);
}