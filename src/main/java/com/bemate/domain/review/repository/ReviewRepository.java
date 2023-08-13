package com.bemate.domain.review.repository;

import com.bemate.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r join fetch r.shelter")
    Page<Review> findAll(Pageable pageable);

    @Query("select r from Review r join fetch r.shelter " +
            "where r.id = :id")
    Optional<Review> findById(@Param("id") Long id);
}
