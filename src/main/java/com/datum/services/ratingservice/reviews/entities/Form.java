package com.datum.services.ratingservice.reviews.entities;

import com.datum.services.ratingservice.core.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@Entity
public class Form extends BaseEntity<Integer> {

    private String name;
    @OneToMany(mappedBy = "form", orphanRemoval = true, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<FormField> formFields;
    @ManyToOne
    private Client client;
}
