package com.datum.services.ratingservice.reviews.entities;


import com.datum.services.ratingservice.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class FormFieldValue extends BaseEntity<Integer> {

    private String value;
    @ManyToOne
    private FormField formField;
}
