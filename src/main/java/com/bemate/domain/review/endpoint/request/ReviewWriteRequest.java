package com.bemate.domain.review.endpoint.request;

import com.bemate.domain.review.entity.Review;
import com.bemate.domain.shelter.entity.Shelter;
import lombok.Data;

@Data
public class ReviewWriteRequest {
    private String title;
    private String content;
    private String shelterName;

    public Review toReview(Shelter shelter) {
        return Review.builder()
                .title(title)
                .content(content)
                .shelter(shelter)
                .build();
    }
}
