package com.datum.services.ratingservice.ratings.client.forms;

import com.datum.services.ratingservice.core.BaseForm;
import lombok.Data;

@Data
public class ClientRatingAnswerForm extends BaseForm {
    private Integer userId;
    private Integer questionId;
    private Integer rating;
    private String comment;
}
