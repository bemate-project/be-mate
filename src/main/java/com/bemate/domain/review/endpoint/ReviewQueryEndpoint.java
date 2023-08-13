package com.bemate.domain.review.endpoint;

import com.bemate.domain.review.endpoint.response.dto.ReviewDto;
import com.bemate.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewQueryEndpoint {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews")
    public ResponseEntity<Page<ReviewDto>> findReviews(Pageable pageable) {
        return ResponseEntity.ok(reviewQueryService.findAll(pageable));
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewDto> findReview(@PathVariable(value = "id") Long reviewNo) {
        return ResponseEntity.ok(reviewQueryService.findOne(reviewNo));
    }
}
