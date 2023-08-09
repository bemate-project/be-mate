package com.bemate.domain.review.endpoint;

import com.bemate.domain.auth.Principal;
import com.bemate.domain.review.endpoint.request.ReviewWriteRequest;
import com.bemate.domain.review.file.ReviewImageFile;
import com.bemate.domain.review.service.ReviewWriteService;
import com.bemate.domain.shelter.service.ShelterQueryService;
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
public class ReviewWriteEndpoint {

    private final ShelterQueryService shelterQueryService;
    private final ReviewWriteService reviewWriteService;

    @PostMapping("/reviews")
    public ResponseEntity<HttpStatus> write(@RequestPart(value = "reviewInfo") @Valid ReviewWriteRequest reviewWriteRequest,
                                                  @RequestPart(value = "images", required = false) List<MultipartFile> requestImages,
                                                  @AuthenticationPrincipal Principal principal) {

        var shelter = shelterQueryService.findByName(reviewWriteRequest.getShelterName());
        var review = reviewWriteRequest.toReview(shelter);

        List<ReviewImageFile> images = requestImages == null
                ? emptyList()
                : requestImages.stream()
                .map(image -> new ReviewImageFile(review, image))
                .toList();

        reviewWriteService.write(review, images);

        return new ResponseEntity(HttpStatus.OK);
    }
}
