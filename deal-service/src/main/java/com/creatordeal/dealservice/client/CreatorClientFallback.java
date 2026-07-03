package com.creatordeal.dealservice.client;

import com.creatordeal.dealservice.dto.external.CreatorResponse;
import com.creatordeal.dealservice.exception.ExternalServiceException;
import org.springframework.stereotype.Component;

@Component
public class CreatorClientFallback implements CreatorClient {

    @Override
    public CreatorResponse getCreatorById(Long id) {
        throw new ExternalServiceException("creator-service is unavailable. Please try again later.");
    }
}
