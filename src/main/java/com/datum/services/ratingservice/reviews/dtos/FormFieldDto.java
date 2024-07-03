package com.datum.services.ratingservice.reviews.dtos;

import com.datum.services.ratingservice.core.BaseDto;
import com.datum.services.ratingservice.reviews.entities.FormField;
import lombok.Data;

import java.util.List;

@Data
public class FormFieldDto extends BaseDto<Integer> {

    private String name;
    private FormField.TYPE type;
    private FormField.VALUE_TYPE valueType;
    private String description;
    private List<String> options;
    private String label;
    private String placeholder;
    private boolean isRequired;
}
