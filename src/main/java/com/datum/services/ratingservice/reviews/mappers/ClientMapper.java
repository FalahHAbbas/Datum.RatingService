package com.datum.services.ratingservice.reviews.mappers;


import com.datum.services.ratingservice.reviews.dtos.ClientDto;
import com.datum.services.ratingservice.reviews.entities.Client;
import com.datum.services.ratingservice.reviews.forms.ClientForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client fromForm(ClientForm client);

    ClientDto toDto(Client client);

    Client map(Client client);

}
