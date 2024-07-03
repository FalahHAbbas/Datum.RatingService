package com.datum.services.ratingservice.reviews.entities;


import com.datum.services.ratingservice.core.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class FormField extends BaseEntity<Integer> {

    private String name;
    private TYPE type;
    private VALUE_TYPE valueType;
    private String description;

    private List<String> options;
    private String label;
    private String placeholder;
    @ManyToOne(targetEntity = Form.class)
    private Form form;
    @Column(name = "form_id", insertable = false, updatable = false)
    private Integer formId;
    private boolean isRequired;
    @OneToMany(mappedBy = "formField", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FormFieldValue> formFieldValues;


    public enum TYPE {
        TEXT,
        TEXTAREA,
        SELECT,
        RADIO,
        CHECKBOX,
        DATE,
        TIME,
        FILE,
        NUMBER,
        EMAIL,
        TEL
    }

    public enum VALUE_TYPE {
        STRING,
        NUMBER,
        DATE,
        TIME,
    }
}
