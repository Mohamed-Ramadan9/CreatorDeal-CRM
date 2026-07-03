package com.creatordeal.notificationservice.mapper;

import com.creatordeal.notificationservice.dto.ReminderResponse;
import com.creatordeal.notificationservice.entity.Reminder;

public class ReminderMapper {

    private ReminderMapper() {
    }

    public static ReminderResponse toResponse(Reminder reminder) {
        return ReminderResponse.builder()
                .id(reminder.getId())
                .creatorId(reminder.getCreatorId())
                .dealId(reminder.getDealId())
                .invoiceId(reminder.getInvoiceId())
                .reminderType(reminder.getReminderType())
                .scheduledAt(reminder.getScheduledAt())
                .sentAt(reminder.getSentAt())
                .status(reminder.getStatus())
                .build();
    }
}
