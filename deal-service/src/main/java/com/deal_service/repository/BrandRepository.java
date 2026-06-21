package com.deal_service.repository;

import com.deal_service.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand>  findByCreator_Id(Long creatorId);
}
