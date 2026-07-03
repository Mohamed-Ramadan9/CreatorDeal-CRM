package com.creatordeal.dealservice.service;

import com.creatordeal.dealservice.dto.CreateDeliverableRequest;
import com.creatordeal.dealservice.dto.DeliverableResponse;
import com.creatordeal.dealservice.dto.UpdateDeliverableRequest;
import com.creatordeal.dealservice.entity.Deal;
import com.creatordeal.dealservice.entity.DealDeliverable;
import com.creatordeal.dealservice.enums.DeliverableStatus;
import com.creatordeal.dealservice.event.DeliverableCreatedEvent;
import com.creatordeal.dealservice.exception.ResourceNotFoundException;
import com.creatordeal.dealservice.mapper.DeliverableMapper;
import com.creatordeal.dealservice.repository.DealDeliverableRepository;
import com.creatordeal.dealservice.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliverableServiceImpl implements DeliverableService {

    private final DealDeliverableRepository deliverableRepository;
    private final DealRepository dealRepository;
    private final StreamBridge streamBridge;

    @Override
    public DeliverableResponse createDeliverable(CreateDeliverableRequest request) {
        Deal deal = dealRepository.findById(request.getDealId())
                .orElseThrow(() -> new ResourceNotFoundException("Deal not found with id: " + request.getDealId()));

        DealDeliverable deliverable = DealDeliverable.builder()
                .deal(deal)
                .type(request.getType())
                .platform(request.getPlatform())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .status(request.getStatus() != null ? request.getStatus() : DeliverableStatus.TODO)
                .postedUrl(request.getPostedUrl())
                .build();

        DealDeliverable savedDeliverable = deliverableRepository.save(deliverable);
        streamBridge.send("deliverableCreated-out-0", new DeliverableCreatedEvent(
                savedDeliverable.getId(),
                deal.getId(),
                deal.getCreatorId(),
                savedDeliverable.getDueDate(),
                savedDeliverable.getType().name()
        ));

        return DeliverableMapper.toResponse(savedDeliverable);
    }

    @Override
    @Transactional(readOnly = true)
    public DeliverableResponse getDeliverableById(Long id) {
        DealDeliverable deliverable = deliverableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deliverable not found with id: " + id));

        return DeliverableMapper.toResponse(deliverable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliverableResponse> getDeliverablesByDeal(Long dealId) {
        return deliverableRepository.findByDeal_Id(dealId)
                .stream()
                .map(DeliverableMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliverableResponse> getDeliverablesByDealAndStatus(Long dealId, DeliverableStatus status) {
        return deliverableRepository.findByDeal_IdAndStatus(dealId, status)
                .stream()
                .map(DeliverableMapper::toResponse)
                .toList();
    }

    @Override
    public DeliverableResponse updateDeliverable(Long id, UpdateDeliverableRequest request) {
        DealDeliverable deliverable = deliverableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deliverable not found with id: " + id));

        if (request.getType() != null) deliverable.setType(request.getType());
        if (request.getPlatform() != null) deliverable.setPlatform(request.getPlatform());
        if (request.getDescription() != null) deliverable.setDescription(request.getDescription());
        if (request.getDueDate() != null) deliverable.setDueDate(request.getDueDate());
        if (request.getStatus() != null) deliverable.setStatus(request.getStatus());
        if (request.getPostedUrl() != null) deliverable.setPostedUrl(request.getPostedUrl());

        return DeliverableMapper.toResponse(deliverableRepository.save(deliverable));
    }

    @Override
    public void deleteDeliverable(Long id) {
        DealDeliverable deliverable = deliverableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deliverable not found with id: " + id));

        deliverableRepository.delete(deliverable);
    }
}
