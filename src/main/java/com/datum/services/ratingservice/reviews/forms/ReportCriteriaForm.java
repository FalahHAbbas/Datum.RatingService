package com.datum.services.ratingservice.reviews.forms;

import com.datum.services.ratingservice.core.BaseForm;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReportCriteriaForm  extends BaseForm {
    private String name;
    private List<ReportFilterForm> filters;
    private Integer sortFieldId;
}
