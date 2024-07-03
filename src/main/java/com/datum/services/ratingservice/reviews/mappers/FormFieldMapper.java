package com.datum.services.ratingservice.reviews.mappers;


import com.datum.services.ratingservice.reviews.dtos.FormFieldDto;
import com.datum.services.ratingservice.reviews.entities.FormField;
import com.datum.services.ratingservice.reviews.forms.FormFieldForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormFieldMapper {

    FormField fromForm(FormFieldForm formField);

    FormFieldDto toDto(FormField formField);

    FormField map(FormField formField);


}
