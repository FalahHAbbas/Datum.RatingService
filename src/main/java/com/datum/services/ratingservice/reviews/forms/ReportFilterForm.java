package com.datum.services.ratingservice.reviews.forms;

import com.datum.services.ratingservice.core.BaseForm;
import com.datum.services.ratingservice.reviews.entities.FormField;
import com.datum.services.ratingservice.reviews.entities.ReportFilter;
import lombok.Data;

@Data
public class ReportFilterForm  extends BaseForm {
    private String startValue;
    private String endValue;
    private FormField.VALUE_TYPE valueType;
    private ReportFilter.Operation operation;
    private Integer formFieldId;

}
