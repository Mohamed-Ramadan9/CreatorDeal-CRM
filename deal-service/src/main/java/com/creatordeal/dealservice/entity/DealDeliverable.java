package com.creatordeal.dealservice.entity;


import com.creatordeal.dealservice.enums.DeliverableStatus;
import com.creatordeal.dealservice.enums.DeliverableType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "deal_deliverables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealDeliverable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_id", nullable = false)
    private Deal deal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private DeliverableType type;

    @Column(length = 50)
    private String platform;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private DeliverableStatus status;

    @Column(name = "posted_url", columnDefinition = "TEXT")
    private String postedUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        if (status == null) {
            status = DeliverableStatus.TODO;
        }

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}