package com.datum.services.ratingservice.reviews.repositories;

import com.datum.services.ratingservice.reviews.entities.FormFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormFieldValueRepository extends JpaRepository<FormFieldValue, Integer> {

}
