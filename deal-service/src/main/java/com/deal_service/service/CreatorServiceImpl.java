package com.deal_service.service;

import com.deal_service.dto.CreateCreatorRequest;
import com.deal_service.dto.CreatorResponse;
import com.deal_service.dto.UpdateCreatorRequest;
import com.deal_service.entity.Creator;
import com.deal_service.exception.DuplicateResourceException;
import com.deal_service.exception.ResourceNotFoundException;
import com.deal_service.mapper.CreatorMapper;
import com.deal_service.repository.CreatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatorServiceImpl implements CreatorService {

    private final CreatorRepository creatorRepository;

    @Override
    public CreatorResponse createCreator(CreateCreatorRequest request) {
        if (creatorRepository.existsByUserId(request.getUserId())) {
            throw new DuplicateResourceException("Creator already exists with userId: " + request.getUserId());
        }

        Creator creator = Creator.builder()
                .userId(request.getUserId())
                .displayName(request.getDisplayName())
                .country(request.getCountry())
                .mainPlatform(request.getMainPlatform())
                .instagramHandle(request.getInstagramHandle())
                .tiktokHandle(request.getTiktokHandle())
                .youtubeHandle(request.getYoutubeHandle())
                .followersCount(request.getFollowersCount())
                .build();

        Creator savedCreator = creatorRepository.save(creator);

        return CreatorMapper.toResponse(savedCreator);
    }

    @Override
    @Transactional(readOnly = true)
    public CreatorResponse getCreatorById(Long id) {
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Creator not found with id: " + id));

        return CreatorMapper.toResponse(creator);
    }

    @Override
    @Transactional(readOnly = true)
    public CreatorResponse getCreatorByUserId(String userId) {
        Creator creator = creatorRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Creator not found with userId: " + userId));

        return CreatorMapper.toResponse(creator);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreatorResponse> getAllCreators() {
        return creatorRepository.findAll()
                .stream()
                .map(CreatorMapper::toResponse)
                .toList();
    }

    @Override
    public CreatorResponse updateCreator(Long id, UpdateCreatorRequest request) {
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Creator not found with id: " + id));

        if (request.getDisplayName() != null) creator.setDisplayName(request.getDisplayName());
        if (request.getCountry() != null) creator.setCountry(request.getCountry());
        if (request.getMainPlatform() != null) creator.setMainPlatform(request.getMainPlatform());
        if (request.getInstagramHandle() != null) creator.setInstagramHandle(request.getInstagramHandle());
        if (request.getTiktokHandle() != null) creator.setTiktokHandle(request.getTiktokHandle());
        if (request.getYoutubeHandle() != null) creator.setYoutubeHandle(request.getYoutubeHandle());
        if (request.getFollowersCount() != null) creator.setFollowersCount(request.getFollowersCount());

        Creator updatedCreator = creatorRepository.save(creator);

        return CreatorMapper.toResponse(updatedCreator);
    }

    @Override
    public void deleteCreator(Long id) {
        Creator creator = creatorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Creator not found with id: " + id));

        creatorRepository.delete(creator);
    }
}
