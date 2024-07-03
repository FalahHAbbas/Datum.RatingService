package com.datum.services.ratingservice.reviews.forms;

import com.datum.services.ratingservice.core.BaseForm;
import lombok.Data;

@Data
public class ClientForm extends BaseForm {

    private String name;
    private String email;

}
