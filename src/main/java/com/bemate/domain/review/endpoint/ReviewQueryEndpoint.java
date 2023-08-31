package com.bemate.domain.review.endpoint;

import com.bemate.domain.review.endpoint.response.dto.ReviewDto;
import com.bemate.domain.review.service.ReviewQueryService;
import com.bemate.domain.shelter.service.ShelterQueryService;
import com.bemate.domain.user.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Review")
public class ReviewQueryEndpoint {

    private final ReviewQueryService reviewQueryService;
    private final ShelterQueryService shelterQueryService;
    private final UserQueryService userQueryService;

    @GetMapping("/reviews")
    @Operation(summary = "Retrieve Reviews")
    public ResponseEntity<Page<ReviewDto>> findReviews(Pageable pageable) {
        return ResponseEntity.ok(reviewQueryService.findAll(pageable));
    }

    @GetMapping("/reviews/{id}")
    @Operation(summary = "Retrieve Review Details")
    public ResponseEntity<ReviewDto> findReview(@PathVariable(value = "id") Long reviewNo) {
        return ResponseEntity.ok(reviewQueryService.findOne(reviewNo));
    }

    @GetMapping("/reviews/shelter/{id}")
    @Operation(summary = "List Reviews By ShelterId")
    public ResponseEntity<Page<ReviewDto>> findByShelter(@PathVariable(value = "id") Long shelterNo,
                                                           Pageable pageable) {
        var shelter = shelterQueryService.findById(shelterNo);
        return ResponseEntity.ok(reviewQueryService.findByShelterNo(shelter, pageable));
    }

    @GetMapping("/reviews/user/{id}")
    @Operation(summary = "List Reviews By UserId")
    public ResponseEntity<Page<ReviewDto>> findByUser(@PathVariable(value = "id") Long userNo,
                                                      Pageable pageable) {
        var user = userQueryService.findById(userNo);
        return ResponseEntity.ok(reviewQueryService.findByUser(user, pageable));
    }
}
