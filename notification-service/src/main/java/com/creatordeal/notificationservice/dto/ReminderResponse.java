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
public class ReminderResponse {

    private Long id;

    private Long creatorId;

    private Long dealId;

    private Long invoiceId;

    private ReminderType reminderType;

    private LocalDateTime scheduledAt;

    private LocalDateTime sentAt;

    private ReminderStatus status;
}
