package com.datum.services.ratingservice.ratings.client.entities;

import com.datum.services.ratingservice.core.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ClientRating extends BaseEntity<Integer> {
    private String name;
    @OneToMany(mappedBy = "clientRating", orphanRemoval = true, cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private List<ClientRatingQuestion> questions;

}
