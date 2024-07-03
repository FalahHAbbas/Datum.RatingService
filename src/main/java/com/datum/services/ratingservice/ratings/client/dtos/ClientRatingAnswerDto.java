package com.datum.services.ratingservice.ratings.client.dtos;

import com.datum.services.ratingservice.core.BaseDto;
import lombok.Data;

@Data
public class ClientRatingAnswerDto extends BaseDto<Integer> {
    private Integer userId;
    private Integer questionId;
    private ClientRatingQuestionDto question;
    private Integer rating;
    private String comment;
}
