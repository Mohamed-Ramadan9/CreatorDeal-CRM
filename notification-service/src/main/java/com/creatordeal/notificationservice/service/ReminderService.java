package com.creatordeal.notificationservice.service;

import com.creatordeal.notificationservice.dto.CreateReminderRequest;
import com.creatordeal.notificationservice.dto.ReminderResponse;
import com.creatordeal.notificationservice.dto.UpdateReminderRequest;
import com.creatordeal.notificationservice.enums.ReminderStatus;

import java.util.List;

public interface ReminderService {

    ReminderResponse createReminder(CreateReminderRequest request);

    ReminderResponse getReminderById(Long id);

    List<ReminderResponse> getRemindersByCreator(Long creatorId);

    List<ReminderResponse> getRemindersByCreatorAndStatus(Long creatorId, ReminderStatus status);

    ReminderResponse updateReminder(Long id, UpdateReminderRequest request);

    ReminderResponse cancelReminder(Long id);

    void deleteReminder(Long id);

    void processDueReminders();
}