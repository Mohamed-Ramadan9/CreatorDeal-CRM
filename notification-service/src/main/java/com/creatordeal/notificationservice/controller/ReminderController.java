package com.creatordeal.notificationservice.controller;

import com.creatordeal.notificationservice.dto.CreateReminderRequest;
import com.creatordeal.notificationservice.dto.ReminderResponse;
import com.creatordeal.notificationservice.dto.UpdateReminderRequest;
import com.creatordeal.notificationservice.enums.ReminderStatus;
import com.creatordeal.notificationservice.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderResponse createReminder(@Valid @RequestBody CreateReminderRequest request) {
        return reminderService.createReminder(request);
    }

    @GetMapping("/{id}")
    public ReminderResponse getReminderById(@PathVariable Long id) {
        return reminderService.getReminderById(id);
    }

    @GetMapping("/creator/{creatorId}")
    public List<ReminderResponse> getRemindersByCreator(@PathVariable Long creatorId) {
        return reminderService.getRemindersByCreator(creatorId);
    }

    @GetMapping("/creator/{creatorId}/status/{status}")
    public List<ReminderResponse> getRemindersByCreatorAndStatus(
            @PathVariable Long creatorId,
            @PathVariable ReminderStatus status
    ) {
        return reminderService.getRemindersByCreatorAndStatus(creatorId, status);
    }

    @PutMapping("/{id}")
    public ReminderResponse updateReminder(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReminderRequest request
    ) {
        return reminderService.updateReminder(id, request);
    }

    @PatchMapping("/{id}/cancel")
    public ReminderResponse cancelReminder(@PathVariable Long id) {
        return reminderService.cancelReminder(id);
    }

    @PostMapping("/process-due")
    public void processDueRemindersManually() {
        reminderService.processDueReminders();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
    }
}