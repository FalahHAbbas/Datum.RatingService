package com.datum.services.ratingservice.ratings.client.entities;

import com.datum.services.ratingservice.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ClientRatingAnswer extends BaseEntity<Integer> {
    private Integer userId;
    @Column(name = "question_id", insertable = false, updatable = false)
    private Integer questionId;
    @ManyToOne
    private ClientRatingQuestion question;
    private Integer rating;
    private String comment;
}
