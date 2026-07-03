package com.creatordeal.notificationservice.dto;

import com.creatordeal.notificationservice.enums.ReminderType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReminderRequest {

    @NotNull
    private Long creatorId;

    private Long dealId;

    private Long invoiceId;

    @NotNull
    private ReminderType reminderType;

    @NotNull
    private LocalDateTime scheduledAt;
}
