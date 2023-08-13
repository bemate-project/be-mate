package com.bemate.domain.review.service;

import com.bemate.domain.review.endpoint.response.dto.ReviewDto;
import com.bemate.domain.review.repository.ReviewRepository;
import com.bemate.global.exception.ReviewNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public Page<ReviewDto> findAll(Pageable pageable) {
        var reviews = reviewRepository.findAll(pageable);
        return reviews.map(ReviewDto::from);
    }

    public ReviewDto findOne(Long reviewNo) {
        var review = reviewRepository.findById(reviewNo)
                .orElseThrow(() -> new ReviewNotFoundException(reviewNo));

        return ReviewDto.from(review);
    }
}
