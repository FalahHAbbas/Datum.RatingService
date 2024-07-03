package com.datum.services.ratingservice.reviews.forms;

import com.datum.services.ratingservice.core.BaseForm;
import lombok.Data;

import java.util.List;

@Data
public class FormForm  extends BaseForm {

    private String name;
    private String email;
    private List<FormFieldForm> formFields;

}
