package com.creatordeal.creatorservice.service;

import com.creatordeal.creatorservice.dto.CreateCreatorRequest;
import com.creatordeal.creatorservice.dto.CreatorResponse;
import com.creatordeal.creatorservice.dto.UpdateCreatorRequest;

import java.util.List;

public interface CreatorService {

    CreatorResponse createCreator(CreateCreatorRequest request);

    CreatorResponse getCreatorById(Long id);

    CreatorResponse getCreatorByUserId(String userId);

    List<CreatorResponse> getAllCreators();

    CreatorResponse updateCreator(Long id, UpdateCreatorRequest request);

    void deleteCreator(Long id);
}
