package com.creatordeal.notificationservice.service;

import com.creatordeal.notificationservice.dto.CreateReminderRequest;
import com.creatordeal.notificationservice.dto.ReminderResponse;
import com.creatordeal.notificationservice.dto.UpdateReminderRequest;
import com.creatordeal.notificationservice.entity.Reminder;
import com.creatordeal.notificationservice.enums.ReminderStatus;
import com.creatordeal.notificationservice.exception.ResourceNotFoundException;
import com.creatordeal.notificationservice.mapper.ReminderMapper;
import com.creatordeal.notificationservice.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;

    @Override
    public ReminderResponse createReminder(CreateReminderRequest request) {
        Reminder reminder = Reminder.builder()
                .creatorId(request.getCreatorId())
                .dealId(request.getDealId())
                .invoiceId(request.getInvoiceId())
                .reminderType(request.getReminderType())
                .scheduledAt(request.getScheduledAt())
                .status(ReminderStatus.PENDING)
                .build();

        return ReminderMapper.toResponse(reminderRepository.save(reminder));
    }

    @Override
    @Transactional(readOnly = true)
    public ReminderResponse getReminderById(Long id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + id));

        return ReminderMapper.toResponse(reminder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReminderResponse> getRemindersByCreator(Long creatorId) {
        return reminderRepository.findByCreatorId(creatorId)
                .stream()
                .map(ReminderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReminderResponse> getRemindersByCreatorAndStatus(Long creatorId, ReminderStatus status) {
        return reminderRepository.findByCreatorIdAndStatus(creatorId, status)
                .stream()
                .map(ReminderMapper::toResponse)
                .toList();
    }

    @Override
    public ReminderResponse updateReminder(Long id, UpdateReminderRequest request) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + id));

        if (request.getReminderType() != null) {
            reminder.setReminderType(request.getReminderType());
        }

        if (request.getScheduledAt() != null) {
            reminder.setScheduledAt(request.getScheduledAt());
        }

        if (request.getStatus() != null) {
            reminder.setStatus(request.getStatus());
        }

        return ReminderMapper.toResponse(reminderRepository.save(reminder));
    }

    @Override
    public ReminderResponse cancelReminder(Long id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + id));

        reminder.setStatus(ReminderStatus.CANCELLED);

        return ReminderMapper.toResponse(reminderRepository.save(reminder));
    }

    @Override
    public void deleteReminder(Long id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + id));

        reminderRepository.delete(reminder);
    }

    @Override
    public void processDueReminders() {
        List<Reminder> dueReminders = reminderRepository.findByScheduledAtBeforeAndStatus(
                LocalDateTime.now(),
                ReminderStatus.PENDING
        );

        for (Reminder reminder : dueReminders) {
            try {
                /*
                 * For now we only log.
                 * Later this will publish event to notification-service.
                 */
                log.info(
                        "Reminder due: id={}, creator={}, type={}, dealId={}, invoiceId={}",
                        reminder.getId(),
                        reminder.getCreatorId(),
                        reminder.getReminderType(),
                        reminder.getDealId(),
                        reminder.getInvoiceId()
                );

                reminder.setStatus(ReminderStatus.SENT);
                reminder.setSentAt(LocalDateTime.now());

                reminderRepository.save(reminder);

            } catch (Exception ex) {
                log.error("Failed to process reminder id={}", reminder.getId(), ex);
                reminder.setStatus(ReminderStatus.FAILED);
                reminderRepository.save(reminder);
            }
        }
    }
}
