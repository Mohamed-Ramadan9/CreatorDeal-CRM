package com.creatordeal.dealservice.service;

import com.creatordeal.dealservice.client.BrandClient;
import com.creatordeal.dealservice.client.CreatorClient;
import com.creatordeal.dealservice.dto.CreateDealRequest;
import com.creatordeal.dealservice.dto.DealResponse;
import com.creatordeal.dealservice.dto.UpdateDealRequest;
import com.creatordeal.dealservice.entity.Deal;
import com.creatordeal.dealservice.enums.DealStatus;
import com.creatordeal.dealservice.event.DealCreatedEvent;
import com.creatordeal.dealservice.exception.ResourceNotFoundException;
import com.creatordeal.dealservice.mapper.DealMapper;
import com.creatordeal.dealservice.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final StreamBridge streamBridge;
    private final CreatorClient creatorClient;
    private final BrandClient brandClient;

    @Override
    public DealResponse createDeal(CreateDealRequest request) {

        creatorClient.getCreatorById(request.getCreatorId());
        brandClient.getBrandById(request.getBrandId());

        Deal deal = Deal.builder()
                .creatorId(request.getCreatorId())
                .brandId(request.getBrandId())
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : DealStatus.LEAD)
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .startDate(request.getStartDate())
                .dueDate(request.getDueDate())
                .build();

        Deal savedDeal = dealRepository.save(deal);
        streamBridge.send("dealCreated-out-0", new DealCreatedEvent(
                savedDeal.getId(),
                savedDeal.getCreatorId(),
                savedDeal.getDueDate(),
                savedDeal.getTitle()
        ));

        return DealMapper.toResponse(savedDeal);
    }

    @Override
    @Transactional(readOnly = true)
    public DealResponse getDealById(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deal not found with id: " + id));

        return DealMapper.toResponse(deal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DealResponse> getDealsByCreator(Long creatorId) {
        return dealRepository.findByCreatorId(creatorId)
                .stream()
                .map(DealMapper::toResponse)
                .toList();
    }

    @Override
    public DealResponse updateDeal(Long id, UpdateDealRequest request) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deal not found with id: " + id));

        if (request.getTitle() != null) {
            deal.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            deal.setDescription(request.getDescription());
        }

        if (request.getStatus() != null) {
            deal.setStatus(request.getStatus());
        }

        if (request.getAmount() != null) {
            deal.setAmount(request.getAmount());
        }

        if (request.getCurrency() != null) {
            deal.setCurrency(request.getCurrency());
        }

        if (request.getStartDate() != null) {
            deal.setStartDate(request.getStartDate());
        }

        if (request.getDueDate() != null) {
            deal.setDueDate(request.getDueDate());
        }

        Deal updatedDeal = dealRepository.save(deal);

        return DealMapper.toResponse(updatedDeal);
    }

    @Override
    public void deleteDeal(Long id) {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deal not found with id: " + id));

        dealRepository.delete(deal);
    }
}
