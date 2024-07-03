package com.datum.services.ratingservice.reviews.dtos;

import com.datum.services.ratingservice.core.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class FormValuesDto extends BaseDto<Integer> {

    private List<String> values;
    private FormFieldDto formField;


}
