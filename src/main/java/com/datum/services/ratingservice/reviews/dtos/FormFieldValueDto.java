package com.datum.services.ratingservice.reviews.dtos;

import com.datum.services.ratingservice.core.BaseDto;
import lombok.Data;

@Data
public class FormFieldValueDto extends BaseDto<Integer> {

    private String value;
    private FormFieldDto formField;


}
