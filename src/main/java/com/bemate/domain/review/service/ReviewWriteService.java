package com.bemate.domain.review.service;

import com.bemate.domain.review.entity.Review;
import com.bemate.domain.review.file.ReviewImageFile;
import com.bemate.domain.review.repository.ReviewRepository;
import com.bemate.global.infra.file.service.S3WriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewWriteService {

    private final S3WriteService s3WriteService;
    private final ReviewRepository reviewRepository;

    public void write(Review review, List<ReviewImageFile> imageFiles) {
        s3WriteService.upload(imageFiles);

        review.addImageFiles(imageFiles);

        reviewRepository.save(review);
    }
}
