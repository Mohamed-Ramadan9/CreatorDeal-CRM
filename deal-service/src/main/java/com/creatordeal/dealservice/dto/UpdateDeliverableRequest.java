package com.creatordeal.dealservice.dto;

import com.creatordeal.dealservice.enums.DeliverableStatus;
import com.creatordeal.dealservice.enums.DeliverableType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDeliverableRequest {

    private DeliverableType type;
    private String platform;
    private String description;
    private LocalDate dueDate;
    private DeliverableStatus status;
    private String postedUrl;
}