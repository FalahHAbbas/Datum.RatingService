package com.datum.services.ratingservice.ratings.client.mappers;

import com.datum.services.ratingservice.ratings.client.dtos.ClientRatingQuestionDto;
import com.datum.services.ratingservice.ratings.client.entities.ClientRatingQuestion;
import com.datum.services.ratingservice.ratings.client.forms.ClientRatingQuestionForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientRatingQuestionMapper {
    ClientRatingQuestion fromForm(ClientRatingQuestionForm clientRatingQuestionForm);

    ClientRatingQuestionDto toDto(ClientRatingQuestion clientRatingQuestion);

    ClientRatingQuestion map(ClientRatingQuestion src);


}
