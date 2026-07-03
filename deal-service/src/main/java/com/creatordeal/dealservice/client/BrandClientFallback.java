package com.creatordeal.dealservice.client;

import com.creatordeal.dealservice.dto.external.BrandResponse;
import com.creatordeal.dealservice.exception.ExternalServiceException;
import org.springframework.stereotype.Component;

@Component
public class BrandClientFallback implements BrandClient {

    @Override
    public BrandResponse getBrandById(Long id) {
        throw new ExternalServiceException("brand-service is unavailable. Please try again later.");
    }
}