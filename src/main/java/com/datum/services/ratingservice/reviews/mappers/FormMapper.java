package com.datum.services.ratingservice.reviews.mappers;


import com.datum.services.ratingservice.reviews.dtos.FormDto;
import com.datum.services.ratingservice.reviews.entities.Form;
import com.datum.services.ratingservice.reviews.forms.FormForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormMapper {
    Form fromForm(FormForm form);

    FormDto toDto(Form form);

    Form map(Form form);

}
