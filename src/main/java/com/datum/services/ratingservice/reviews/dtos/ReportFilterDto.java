package com.datum.services.ratingservice.reviews.dtos;

import com.datum.services.ratingservice.core.BaseDto;
import com.datum.services.ratingservice.reviews.entities.FormField;
import com.datum.services.ratingservice.reviews.entities.ReportCriteria;
import com.datum.services.ratingservice.reviews.entities.ReportFilter;
import lombok.Data;

import java.util.UUID;

@Data
public class ReportFilterDto extends BaseDto<Integer> {

    private String startValue;
    private String endValue;
    private FormField.VALUE_TYPE valueType;
    private ReportFilter.Operation operation;
    private Integer formFieldId;
    private FormField formField;
    private ReportCriteria reportCriteria;
    private Integer reportCriteriaId;

}
