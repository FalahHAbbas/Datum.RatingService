package com.datum.services.ratingservice.core;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BaseDto<TId> {
    private TId id;
    private LocalDate createdDate = LocalDate.now();
    private LocalDate updatedDate = LocalDate.now();
    private Integer createdBy;
    private Integer updatedBy;
}
