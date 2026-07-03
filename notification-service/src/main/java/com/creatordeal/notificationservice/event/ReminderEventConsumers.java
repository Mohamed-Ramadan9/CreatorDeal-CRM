package com.creatordeal.notificationservice.event;

import com.creatordeal.notificationservice.entity.Reminder;
import com.creatordeal.notificationservice.enums.ReminderStatus;
import com.creatordeal.notificationservice.enums.ReminderType;
import com.creatordeal.notificationservice.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class ReminderEventConsumers {

    private static final LocalTime REMINDER_TIME = LocalTime.of(9, 0);

    private final ReminderRepository reminderRepository;

    @Bean
    public Consumer<DealCreatedEvent> dealCreated() {
        return event -> {
            if (event.dueDate() != null) {
                reminderRepository.save(Reminder.builder()
                        .creatorId(event.creatorId())
                        .dealId(event.dealId())
                        .reminderType(ReminderType.DEAL_DEADLINE)
                        .scheduledAt(event.dueDate().atTime(REMINDER_TIME))
                        .status(ReminderStatus.PENDING)
                        .build());
            }
        };
    }

    @Bean
    public Consumer<DeliverableCreatedEvent> deliverableCreated() {
        return event -> {
            if (event.dueDate() != null) {
                reminderRepository.save(Reminder.builder()
                        .creatorId(event.creatorId())
                        .dealId(event.dealId())
                        .reminderType(ReminderType.DELIVERABLE_DUE)
                        .scheduledAt(event.dueDate().atTime(REMINDER_TIME))
                        .status(ReminderStatus.PENDING)
                        .build());
            }
        };
    }

    @Bean
    public Consumer<InvoiceCreatedEvent> invoiceCreated() {
        return event -> {
            if (event.dueDate() != null) {
                reminderRepository.save(Reminder.builder()
                        .creatorId(event.creatorId())
                        .dealId(event.dealId())
                        .invoiceId(event.invoiceId())
                        .reminderType(ReminderType.INVOICE_DUE)
                        .scheduledAt(event.dueDate().atTime(REMINDER_TIME))
                        .status(ReminderStatus.PENDING)
                        .build());
            }
        };
    }
}
