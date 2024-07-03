package com.datum.services.ratingservice.core;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseEntity<TId> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private TId id;
    private LocalDate createdDate = LocalDate.now();
    private LocalDate updatedDate = LocalDate.now();
    private Integer createdBy;
    private Integer updatedBy;
    private boolean isDeleted;
}
