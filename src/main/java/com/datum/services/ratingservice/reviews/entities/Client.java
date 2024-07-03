package com.datum.services.ratingservice.reviews.entities;

import com.datum.services.ratingservice.core.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Client extends BaseEntity<Integer> {

    private String name;
    private String email;
    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Form> forms;
}
