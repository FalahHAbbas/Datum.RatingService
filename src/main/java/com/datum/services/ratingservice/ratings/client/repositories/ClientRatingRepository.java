package com.datum.services.ratingservice.ratings.client.repositories;

import com.datum.services.ratingservice.ratings.client.entities.ClientRating;
import com.datum.services.ratingservice.ratings.client.filters.ClientRatingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRatingRepository extends JpaRepository<ClientRating, Integer> {

    //    @Query("""
//            SELECT cr FROM ClientRating cr WHERE
//            (:#{#filter?.clientId} IS NULL OR cr.clientId = :#{#filter?.clientId}) AND\s
//            (:#{#filter?.userId} IS NULL OR cr.userId = :#{#filter?.userId}) AND
//            (:#{#filter?.rating} IS NULL OR cr.rating = :#{#filter?.rating}) AND
//            (:#{#filter?.comment} IS NULL OR cr.comment like %:#{#filter?.comment}%)
//            """)
    @Query("""
            SELECT cr FROM ClientRating cr
            """)
    Page<ClientRating> findAllFiltered(ClientRatingFilter filter, Pageable pageable);
}
