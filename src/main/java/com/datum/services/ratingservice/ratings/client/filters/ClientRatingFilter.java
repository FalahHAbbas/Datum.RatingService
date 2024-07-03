package com.datum.services.ratingservice.ratings.client.filters;

import lombok.Data;

@Data
public class ClientRatingFilter {
    private Integer clientId;
    private Integer userId;
    private Integer rating;
    private String comment;
}
