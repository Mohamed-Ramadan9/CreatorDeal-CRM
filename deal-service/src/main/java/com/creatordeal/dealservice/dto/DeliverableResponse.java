package com.creatordeal.dealservice.dto;

import com.creatordeal.dealservice.enums.DeliverableStatus;
import com.creatordeal.dealservice.enums.DeliverableType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliverableResponse {

    private Long id;
    private Long dealId;
    private DeliverableType type;
    private String platform;
    private String description;
    private LocalDate dueDate;
    private DeliverableStatus status;
    private String postedUrl;
    private LocalDateTime createdAt;
}
