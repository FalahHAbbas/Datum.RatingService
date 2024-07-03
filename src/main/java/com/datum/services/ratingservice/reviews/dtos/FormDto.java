package com.datum.services.ratingservice.reviews.dtos;

import com.datum.services.ratingservice.core.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class FormDto extends BaseDto<Integer> {

    private String name;
    private String email;
    private List<FormFieldDto> formFields;

}
