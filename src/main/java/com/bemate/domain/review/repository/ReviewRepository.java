package com.bemate.domain.review.repository;

import com.bemate.domain.review.entity.Review;
import com.bemate.domain.shelter.entity.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r join fetch r.shelter")
    Page<Review> findAll(Pageable pageable);

    @Query("select r from Review r join fetch r.shelter " +
            "where r.id = :id")
    Optional<Review> findById(@Param("id") Long id);

    @Query("select r from Review r join fetch r.shelter " +
            "where r.shelter = :shelter")
    Page<Review> findByShelter(@Param("shelter") Shelter shelter, Pageable pageable);
}
