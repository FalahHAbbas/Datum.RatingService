package com.datum.services.ratingservice.reviews.forms;

import com.datum.services.ratingservice.core.BaseForm;
import lombok.Data;

import java.util.UUID;

@Data
public class FormFieldValueForm  extends BaseForm {
    private String value;
    private Integer formFieldId;
}
