package com.creatordeal.invoiceservice.client;

import com.creatordeal.invoiceservice.dto.external.CreatorResponse;
import com.creatordeal.invoiceservice.exception.ExternalServiceException;
import org.springframework.stereotype.Component;

@Component
public class CreatorClientFallback implements CreatorClient {

    @Override
    public CreatorResponse getCreatorById(Long id) {
        throw new ExternalServiceException("creator-service is unavailable. Please try again later.");
    }
}
