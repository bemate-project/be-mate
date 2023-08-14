package com.bemate.domain.review.endpoint;

import com.bemate.domain.review.endpoint.response.dto.ReviewDto;
import com.bemate.domain.review.service.ReviewQueryService;
import com.bemate.domain.shelter.service.ShelterQueryService;
import com.bemate.domain.user.service.UserQueryService;
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
    private final ShelterQueryService shelterQueryService;
    private final UserQueryService userQueryService;

    @GetMapping("/reviews")
    public ResponseEntity<Page<ReviewDto>> findReviews(Pageable pageable) {
        return ResponseEntity.ok(reviewQueryService.findAll(pageable));
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewDto> findReview(@PathVariable(value = "id") Long reviewNo) {
        return ResponseEntity.ok(reviewQueryService.findOne(reviewNo));
    }

    @GetMapping("/reviews/shelter/{id}")
    public ResponseEntity<Page<ReviewDto>> findByShelter(@PathVariable(value = "id") Long shelterNo,
                                                           Pageable pageable) {
        var shelter = shelterQueryService.findById(shelterNo);
        return ResponseEntity.ok(reviewQueryService.findByShelterNo(shelter, pageable));
    }

    @GetMapping("/reviews/user/{id}")
    public ResponseEntity<Page<ReviewDto>> findByUser(@PathVariable(value = "id") Long userNo,
                                                      Pageable pageable) {
        var user = userQueryService.findById(userNo);
        return ResponseEntity.ok(reviewQueryService.findByUser(user, pageable));
    }
}
