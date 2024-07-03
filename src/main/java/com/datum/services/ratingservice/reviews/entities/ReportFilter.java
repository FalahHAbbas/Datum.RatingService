package com.datum.services.ratingservice.reviews.entities;


import com.datum.services.ratingservice.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ReportFilter extends BaseEntity<Integer> {
    private String startValue;
    private String endValue;
    private FormField.VALUE_TYPE valueType;
    private Operation operation;
    @Column(name = "form_field_id",insertable=false, updatable=false)
    private Integer formFieldId;
    @ManyToOne
    private FormField formField;
    @ManyToOne
    private ReportCriteria reportCriteria;
    @Column(name = "report_criteria_id",insertable=false, updatable=false)
    private Integer reportCriteriaId;

    public enum Operation {
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        LESS_THAN,
        BETWEEN,
        IN,
        NOT_IN,
        CONTAINS,
        NOT_CONTAINS,
        IS_EMPTY,
        IS_NOT_EMPTY,
        IS_TRUE,
        IS_FALSE,
    }
}
