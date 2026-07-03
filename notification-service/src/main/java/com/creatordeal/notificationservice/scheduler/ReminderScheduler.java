package com.creatordeal.notificationservice.scheduler;

import com.creatordeal.notificationservice.service.ReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final ReminderService reminderService;

    @Scheduled(fixedDelay = 60000)
    public void processDueReminders() {
        log.info("Checking due reminders...");
        reminderService.processDueReminders();
    }
}
