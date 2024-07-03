package com.datum.services.ratingservice.reviews.entities;

import com.datum.services.ratingservice.core.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ReportCriteria extends BaseEntity<Integer> {
    private String name;
    @OneToMany(mappedBy = "reportCriteria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportFilter> filters;
    private Integer sortFieldId;
}
