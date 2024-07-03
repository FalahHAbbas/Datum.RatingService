package com.datum.services.ratingservice.ratings.client.forms;

import com.datum.services.ratingservice.core.BaseForm;
import lombok.Data;

import java.util.List;

@Data
public class ClientRatingForm extends BaseForm {
    private String name;
    private List<ClientRatingQuestionForm> questions;

}
