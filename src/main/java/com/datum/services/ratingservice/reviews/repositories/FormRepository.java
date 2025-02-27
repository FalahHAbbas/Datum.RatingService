package com.datum.services.ratingservice.reviews.repositories;

import com.datum.services.ratingservice.reviews.entities.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Integer> {

}
