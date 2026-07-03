package com.creatordeal.dealservice.controller;

import com.creatordeal.dealservice.dto.CreateDeliverableRequest;
import com.creatordeal.dealservice.dto.DeliverableResponse;
import com.creatordeal.dealservice.dto.UpdateDeliverableRequest;
import com.creatordeal.dealservice.enums.DeliverableStatus;
import com.creatordeal.dealservice.service.DeliverableService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliverables")
@RequiredArgsConstructor
public class DeliverableController {

    private final DeliverableService deliverableService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliverableResponse createDeliverable(@Valid @RequestBody CreateDeliverableRequest request) {
        return deliverableService.createDeliverable(request);
    }

    @GetMapping("/{id}")
    public DeliverableResponse getDeliverableById(@PathVariable Long id) {
        return deliverableService.getDeliverableById(id);
    }

    @GetMapping("/deal/{dealId}")
    public List<DeliverableResponse> getDeliverablesByDeal(@PathVariable Long dealId) {
        return deliverableService.getDeliverablesByDeal(dealId);
    }

    @GetMapping("/deal/{dealId}/status/{status}")
    public List<DeliverableResponse> getDeliverablesByDealAndStatus(
            @PathVariable Long dealId,
            @PathVariable DeliverableStatus status
    ) {
        return deliverableService.getDeliverablesByDealAndStatus(dealId, status);
    }

    @PutMapping("/{id}")
    public DeliverableResponse updateDeliverable(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDeliverableRequest request
    ) {
        return deliverableService.updateDeliverable(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeliverable(@PathVariable Long id) {
        deliverableService.deleteDeliverable(id);
    }
}