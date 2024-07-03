package com.datum.services.ratingservice.ratings.client.dtos;

import com.datum.services.ratingservice.core.BaseDto;
import lombok.Data;

@Data
public class ClientRatingQuestionDto extends BaseDto<Integer> {
    private String text;

}
