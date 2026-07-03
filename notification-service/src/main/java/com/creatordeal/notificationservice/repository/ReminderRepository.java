package com.creatordeal.notificationservice.repository;

import com.creatordeal.notificationservice.entity.Reminder;
import com.creatordeal.notificationservice.enums.ReminderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByCreatorId(Long creatorId);

    List<Reminder> findByStatus(ReminderStatus status);

    List<Reminder> findByScheduledAtBeforeAndStatus(
            LocalDateTime scheduledAt,
            ReminderStatus status
    );
    List<Reminder> findByCreatorIdAndStatus(
            Long creatorId,
            ReminderStatus status
    );
}
