package com.creatordeal.creatorservice.controller;

import com.creatordeal.creatorservice.dto.CreateCreatorRequest;
import com.creatordeal.creatorservice.dto.CreatorResponse;
import com.creatordeal.creatorservice.dto.UpdateCreatorRequest;
import com.creatordeal.creatorservice.service.CreatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creators")
@RequiredArgsConstructor
public class CreatorController {

    private final CreatorService creatorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatorResponse createCreator(@Valid @RequestBody CreateCreatorRequest request) {
        return creatorService.createCreator(request);
    }

    @GetMapping("/{id}")
    public CreatorResponse getCreatorById(@PathVariable Long id) {
        return creatorService.getCreatorById(id);
    }

    @GetMapping("/user/{userId}")
    public CreatorResponse getCreatorByUserId(@PathVariable String userId) {
        return creatorService.getCreatorByUserId(userId);
    }

    @GetMapping
    public List<CreatorResponse> getAllCreators() {
        return creatorService.getAllCreators();
    }

    @PutMapping("/{id}")
    public CreatorResponse updateCreator(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCreatorRequest request
    ) {
        return creatorService.updateCreator(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreator(@PathVariable Long id) {
        creatorService.deleteCreator(id);
    }
}
