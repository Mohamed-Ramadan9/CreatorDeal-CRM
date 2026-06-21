package com.deal_service.service;


import com.deal_service.dto.BrandResponse;
import com.deal_service.dto.CreateBrandRequest;
import com.deal_service.dto.UpdateBrandRequest;

import java.util.List;

public interface BrandService {

    BrandResponse createBrand(CreateBrandRequest request);

    BrandResponse getBrandById(Long id);

    List<BrandResponse> getBrandsByCreator(Long creatorId);

    BrandResponse updateBrand(Long id, UpdateBrandRequest request);

    void deleteBrand(Long id);
}