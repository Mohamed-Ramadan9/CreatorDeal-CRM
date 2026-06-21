package com.deal_service.controller;

import com.deal_service.dto.BrandResponse;
import com.deal_service.dto.CreateBrandRequest;
import com.deal_service.dto.UpdateBrandRequest;
import com.deal_service.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandResponse createBrand(@Valid @RequestBody CreateBrandRequest request) {
        return brandService.createBrand(request);
    }

    @GetMapping("/{id}")
    public BrandResponse getBrandById(@PathVariable Long id) {
        return brandService.getBrandById(id);
    }

    @GetMapping("/creator/{creatorId}")
    public List<BrandResponse> getBrandsByCreator(@PathVariable Long creatorId) {
        return brandService.getBrandsByCreator(creatorId);
    }

    @PutMapping("/{id}")
    public BrandResponse updateBrand(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBrandRequest request
    ) {
        return brandService.updateBrand(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
    }
}