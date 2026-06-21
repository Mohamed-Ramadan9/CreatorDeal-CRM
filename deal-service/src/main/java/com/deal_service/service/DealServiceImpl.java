package com.deal_service.service;

import com.deal_service.dto.CreateDealRequest;
import com.deal_service.dto.DealResponse;
import com.deal_service.dto.UpdateDealRequest;
import com.deal_service.entity.Brand;
import com.deal_service.entity.Creator;
import com.deal_service.entity.Deal;
import com.deal_service.enums.DealStatus;
import com.deal_service.exception.ResourceNotFoundException;
import com.deal_service.mapper.DealMapper;
import com.deal_service.repository.BrandRepository;
import com.deal_service.repository.CreatorRepository;
import com.deal_service.repository.DealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final CreatorRepository creatorRepository;
    private final BrandRepository brandRepository;

    @Override
    public DealResponse createDeal(CreateDealRequest request) {
        Creator creator = creatorRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Creator not found with id: " + request.getCreatorId()));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + request.getBrandId()));

        Deal deal = Deal.builder()
                .creator(creator)
                .brand(brand)
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : DealStatus.LEAD)
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .startDate(request.getStartDate())
                .dueDate(request.getDueDate())
                .build();

        Deal savedDeal = dealRepository.save(deal);

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
        return dealRepository.findByCreator_Id(creatorId)
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