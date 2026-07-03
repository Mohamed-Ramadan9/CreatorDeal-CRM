package com.creatordeal.brandservice.repository;



import com.creatordeal.brandservice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByCreatorId(Long creatorId);
}
