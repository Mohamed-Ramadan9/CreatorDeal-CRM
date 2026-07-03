package com.creatordeal.dealservice.client;

import com.creatordeal.dealservice.dto.external.BrandResponse;
import com.creatordeal.dealservice.exception.ExternalServiceException;
import com.creatordeal.dealservice.exception.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@FeignClient(
        name = "brand-service",
        fallback = BrandClientFallback.class
)
public interface BrandClient {

    @GetMapping("/api/brands/{id}")
    BrandResponse getBrandById(@PathVariable("id") Long id);
}