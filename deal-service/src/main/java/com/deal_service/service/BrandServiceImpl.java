package com.deal_service.service;

import com.deal_service.dto.BrandResponse;
import com.deal_service.dto.CreateBrandRequest;
import com.deal_service.dto.UpdateBrandRequest;
import com.deal_service.entity.Brand;
import com.deal_service.entity.Creator;
import com.deal_service.exception.ResourceNotFoundException;
import com.deal_service.mapper.BrandMapper;
import com.deal_service.repository.BrandRepository;
import com.deal_service.repository.CreatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final CreatorRepository creatorRepository;

    @Override
    public BrandResponse createBrand(CreateBrandRequest request) {
        Creator creator = creatorRepository.findById(request.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Creator not found with id: " + request.getCreatorId()));

        Brand brand = Brand.builder()
                .creator(creator)
                .brandName(request.getBrandName())
                .contactName(request.getContactName())
                .contactEmail(request.getContactEmail())
                .instagramHandle(request.getInstagramHandle())
                .website(request.getWebsite())
                .notes(request.getNotes())
                .build();

        return BrandMapper.toResponse(brandRepository.save(brand));
    }

    @Override
    @Transactional(readOnly = true)
    public BrandResponse getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));

        return BrandMapper.toResponse(brand);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandResponse> getBrandsByCreator(Long creatorId) {
        return brandRepository.findByCreator_Id(creatorId)
                .stream()
                .map(BrandMapper::toResponse)
                .toList();
    }

    @Override
    public BrandResponse updateBrand(Long id, UpdateBrandRequest request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));

        if (request.getBrandName() != null) brand.setBrandName(request.getBrandName());
        if (request.getContactName() != null) brand.setContactName(request.getContactName());
        if (request.getContactEmail() != null) brand.setContactEmail(request.getContactEmail());
        if (request.getInstagramHandle() != null) brand.setInstagramHandle(request.getInstagramHandle());
        if (request.getWebsite() != null) brand.setWebsite(request.getWebsite());
        if (request.getNotes() != null) brand.setNotes(request.getNotes());

        return BrandMapper.toResponse(brandRepository.save(brand));
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: " + id));

        brandRepository.delete(brand);
    }
}
