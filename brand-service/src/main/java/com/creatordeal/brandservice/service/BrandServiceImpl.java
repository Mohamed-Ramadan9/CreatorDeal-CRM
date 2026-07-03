package com.creatordeal.brandservice.service;

import com.creatordeal.brandservice.dto.BrandResponse;
import com.creatordeal.brandservice.dto.CreateBrandRequest;
import com.creatordeal.brandservice.dto.UpdateBrandRequest;
import com.creatordeal.brandservice.entity.Brand;
import com.creatordeal.brandservice.exception.ResourceNotFoundException;
import com.creatordeal.brandservice.mapper.BrandMapper;
import com.creatordeal.brandservice.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public BrandResponse createBrand(CreateBrandRequest request) {
        Brand brand = Brand.builder()
                .creatorId(request.getCreatorId())
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
        return brandRepository.findByCreatorId(creatorId)
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
