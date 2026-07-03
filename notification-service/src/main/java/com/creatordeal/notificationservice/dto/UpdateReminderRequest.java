package com.creatordeal.notificationservice.dto;

import com.creatordeal.notificationservice.enums.ReminderStatus;
import com.creatordeal.notificationservice.enums.ReminderType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReminderRequest {

    private ReminderType reminderType;

    private LocalDateTime scheduledAt;

    private ReminderStatus status;
}