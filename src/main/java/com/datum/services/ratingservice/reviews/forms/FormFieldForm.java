package com.datum.services.ratingservice.reviews.forms;

import com.datum.services.ratingservice.core.BaseForm;
import com.datum.services.ratingservice.reviews.entities.FormField;
import lombok.Data;

import java.util.List;

@Data
public class FormFieldForm  extends BaseForm {

    private String name;
    private FormField.TYPE type;
    private FormField.VALUE_TYPE valueType;
    private String description;
    private List<String> options;
    private String label;
    private String placeholder;
    private boolean isRequired;

}
