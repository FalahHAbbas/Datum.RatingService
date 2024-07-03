package com.datum.services.ratingservice.ratings.client.forms;

import com.datum.services.ratingservice.core.BaseForm;
import lombok.Data;

@Data
public class ClientRatingQuestionForm extends BaseForm {
    private String text;
}
