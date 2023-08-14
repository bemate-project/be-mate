package com.bemate.domain.review.endpoint.request;

import com.bemate.domain.review.entity.Review;
import com.bemate.domain.shelter.entity.Shelter;
import com.bemate.domain.user.entity.User;
import lombok.Data;

@Data
public class ReviewWriteRequest {
    private String title;
    private String content;
    private String shelterName;

    public Review toReview(Shelter shelter, User user) {
        return Review.builder()
                .title(title)
                .content(content)
                .shelter(shelter)
                .user(user)
                .build();
    }
}
