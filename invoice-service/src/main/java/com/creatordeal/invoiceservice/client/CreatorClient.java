package com.creatordeal.invoiceservice.client;

import com.creatordeal.invoiceservice.dto.external.CreatorResponse;
import com.creatordeal.invoiceservice.exception.ExternalServiceException;
import com.creatordeal.invoiceservice.exception.ResourceNotFoundException;
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
        name = "creator-service",
        fallback = CreatorClientFallback.class
)
public interface CreatorClient {

@GetMapping("/api/creators/{id}")
    CreatorResponse getCreatorById(@PathVariable("id") Long id);

}
