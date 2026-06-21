package com.deal_service.controller;

import com.deal_service.dto.CreateDealRequest;
import com.deal_service.dto.DealResponse;
import com.deal_service.dto.UpdateDealRequest;
import com.deal_service.service.DealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DealResponse createDeal(@Valid @RequestBody CreateDealRequest request) {
        return dealService.createDeal(request);
    }

    @GetMapping("/{id}")
    public DealResponse getDealById(@PathVariable Long id) {
        return dealService.getDealById(id);
    }

    @GetMapping("/creator/{creatorId}")
    public List<DealResponse> getDealsByCreator(@PathVariable Long creatorId) {
        return dealService.getDealsByCreator(creatorId);
    }

    @PutMapping("/{id}")
    public DealResponse updateDeal(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDealRequest request
    ) {
        return dealService.updateDeal(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeal(@PathVariable Long id) {
        dealService.deleteDeal(id);
    }
}
