package com.bemate.domain.review.endpoint;

import com.bemate.domain.auth.Principal;
import com.bemate.domain.review.endpoint.request.ReviewWriteRequest;
import com.bemate.domain.review.file.ReviewImageFile;
import com.bemate.domain.review.service.ReviewWriteService;
import com.bemate.domain.shelter.service.ShelterQueryService;
import com.bemate.domain.user.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Collections.emptyList;

@RestController
@RequiredArgsConstructor
@Tag(name = "Review")
public class ReviewWriteEndpoint {

    private final ShelterQueryService shelterQueryService;
    private final ReviewWriteService reviewWriteService;
    private final UserQueryService userQueryService;

    @PostMapping("/reviews")
    @Operation(summary = "Write Review")
    public ResponseEntity<HttpStatus> write(@RequestPart(value = "reviewInfo") @Valid ReviewWriteRequest reviewWriteRequest,
                                            @RequestPart(value = "images", required = false) List<MultipartFile> requestImages,
                                            @AuthenticationPrincipal Principal principal) {

        var shelter = shelterQueryService.findByName(reviewWriteRequest.getShelterName());
        var user = userQueryService.findById(principal.getUserNo());
        var review = reviewWriteRequest.toReview(shelter, user);

        List<ReviewImageFile> images = requestImages == null
                ? emptyList()
                : requestImages.stream()
                .map(image -> new ReviewImageFile(review, image))
                .toList();

        reviewWriteService.write(review, images);

        return new ResponseEntity(HttpStatus.OK);
    }
}
