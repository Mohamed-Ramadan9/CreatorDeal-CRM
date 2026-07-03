package com.creatordeal.invoiceservice.client;

import com.creatordeal.invoiceservice.dto.external.DealResponse;
import com.creatordeal.invoiceservice.exception.ExternalServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;

@Component
public class DealClientFallback implements DealClient {
    @Override
    public DealResponse getDealById(Long id) {
        throw new ExternalServiceException("deal-service is unavailable. Please try again later.");
    }
}
