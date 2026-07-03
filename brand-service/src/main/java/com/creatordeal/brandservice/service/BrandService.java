package com.creatordeal.brandservice.service;


import com.creatordeal.brandservice.dto.BrandResponse;
import com.creatordeal.brandservice.dto.CreateBrandRequest;
import com.creatordeal.brandservice.dto.UpdateBrandRequest;

import java.util.List;

public interface BrandService {

    BrandResponse createBrand(CreateBrandRequest request);

    BrandResponse getBrandById(Long id);

    List<BrandResponse> getBrandsByCreator(Long creatorId);

    BrandResponse updateBrand(Long id, UpdateBrandRequest request);

    void deleteBrand(Long id);
}