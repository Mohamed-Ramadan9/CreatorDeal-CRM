package com.creatordeal.dealservice.service;

import com.creatordeal.dealservice.dto.CreateDealRequest;
import com.creatordeal.dealservice.dto.DealResponse;
import com.creatordeal.dealservice.dto.UpdateDealRequest;

import java.util.List;

public interface DealService {

    DealResponse createDeal(CreateDealRequest request);

    DealResponse getDealById(Long id);

    List<DealResponse> getDealsByCreator(Long creatorId);

    DealResponse updateDeal(Long id, UpdateDealRequest request);

    void deleteDeal(Long id);
}