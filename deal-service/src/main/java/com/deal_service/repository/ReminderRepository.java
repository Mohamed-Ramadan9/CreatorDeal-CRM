package com.deal_service.repository;

import com.deal_service.entity.Reminder;
import com.deal_service.enums.ReminderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByStatus(ReminderStatus status);

    List<Reminder> findByScheduledAtBeforeAndStatus(
            LocalDateTime scheduledAt,
            ReminderStatus status
    );
}