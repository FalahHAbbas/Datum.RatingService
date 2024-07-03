package com.datum.services.ratingservice.ratings.client.entities;

import com.datum.services.ratingservice.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ClientRatingQuestion extends BaseEntity<Integer> {
    @Column(name = "client_rating_id", insertable = false, updatable = false)
    private Integer clientRatingId;
    @ManyToOne
    private ClientRating clientRating;
    private String text;
    @OneToMany(mappedBy = "question", orphanRemoval = true, cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<ClientRatingAnswer> answers;

}
