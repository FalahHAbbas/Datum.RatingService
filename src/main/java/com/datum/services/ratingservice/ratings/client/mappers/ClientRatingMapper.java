package com.datum.services.ratingservice.ratings.client.mappers;

import com.datum.services.ratingservice.ratings.client.dtos.ClientRatingDto;
import com.datum.services.ratingservice.ratings.client.entities.ClientRating;
import com.datum.services.ratingservice.ratings.client.forms.ClientRatingForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientRatingMapper {
    ClientRating fromForm(ClientRatingForm clientRatingForm);

    ClientRatingDto toDto(ClientRating clientRating);

    ClientRating map(ClientRating src);


}
