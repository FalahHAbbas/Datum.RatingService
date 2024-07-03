package com.datum.services.ratingservice.ratings.client.dtos;

import com.datum.services.ratingservice.core.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class ClientRatingDto extends BaseDto<Integer> {
    private String name;
    private List<ClientRatingQuestionDto> questions;
}
