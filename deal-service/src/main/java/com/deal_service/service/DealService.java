package com.deal_service.service;

import com.deal_service.dto.CreateDealRequest;
import com.deal_service.dto.DealResponse;
import com.deal_service.dto.UpdateDealRequest;

import java.util.List;

public interface DealService {

    DealResponse createDeal(CreateDealRequest request);

    DealResponse getDealById(Long id);

    List<DealResponse> getDealsByCreator(Long creatorId);

    DealResponse updateDeal(Long id, UpdateDealRequest request);

    void deleteDeal(Long id);
}