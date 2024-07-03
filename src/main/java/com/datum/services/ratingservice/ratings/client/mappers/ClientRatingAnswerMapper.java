package com.datum.services.ratingservice.ratings.client.mappers;

import com.datum.services.ratingservice.ratings.client.dtos.ClientRatingAnswerDto;
import com.datum.services.ratingservice.ratings.client.entities.ClientRatingAnswer;
import com.datum.services.ratingservice.ratings.client.forms.ClientRatingAnswerForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientRatingAnswerMapper {
    ClientRatingAnswer fromForm(ClientRatingAnswerForm clientRatingAnswerForm);

    ClientRatingAnswerDto toDto(ClientRatingAnswer clientRatingAnswer);

    ClientRatingAnswer map(ClientRatingAnswer src);


}
