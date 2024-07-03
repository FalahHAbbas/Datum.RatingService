package com.datum.services.ratingservice.reviews.mappers;


import com.datum.services.ratingservice.reviews.dtos.FormFieldValueDto;
import com.datum.services.ratingservice.reviews.entities.FormFieldValue;
import com.datum.services.ratingservice.reviews.forms.FormFieldValueForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormFieldValueMapper {
    FormFieldValue fromForm(FormFieldValueForm formFieldValue);

    FormFieldValueDto toDto(FormFieldValue formFieldValue);

    FormFieldValue map(FormFieldValue formFieldValue);

}
