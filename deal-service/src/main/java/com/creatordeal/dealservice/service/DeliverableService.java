package com.creatordeal.dealservice.service;

import com.creatordeal.dealservice.dto.CreateDeliverableRequest;
import com.creatordeal.dealservice.dto.DeliverableResponse;
import com.creatordeal.dealservice.dto.UpdateDeliverableRequest;
import com.creatordeal.dealservice.enums.DeliverableStatus;

import java.util.List;

public interface DeliverableService {

    DeliverableResponse createDeliverable(CreateDeliverableRequest request);

    DeliverableResponse getDeliverableById(Long id);

    List<DeliverableResponse> getDeliverablesByDeal(Long dealId);

    List<DeliverableResponse> getDeliverablesByDealAndStatus(Long dealId, DeliverableStatus status);

    DeliverableResponse updateDeliverable(Long id, UpdateDeliverableRequest request);

    void deleteDeliverable(Long id);
}