package com.deal_service.service;

import com.deal_service.dto.CreateCreatorRequest;
import com.deal_service.dto.CreatorResponse;
import com.deal_service.dto.UpdateCreatorRequest;

import java.util.List;

public interface CreatorService {

    CreatorResponse createCreator(CreateCreatorRequest request);

    CreatorResponse getCreatorById(Long id);

    CreatorResponse getCreatorByUserId(String userId);

    List<CreatorResponse> getAllCreators();

    CreatorResponse updateCreator(Long id, UpdateCreatorRequest request);

    void deleteCreator(Long id);
}
