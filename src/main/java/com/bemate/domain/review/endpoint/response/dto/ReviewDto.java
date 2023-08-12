package com.bemate.domain.review.endpoint.response.dto;

import com.bemate.domain.review.entity.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private Long id;
    private String title;
    private String content;
    private String imageFolder;
    private String imageFiles;

    public static ReviewDto from(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .imageFolder(review.getImageFolder())
                .imageFiles(review.getImageFiles())
                .build();
    }
}
