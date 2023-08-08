package com.bemate.domain.review.file;

import com.bemate.domain.review.entity.Review;
import com.bemate.global.infra.file.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import static com.bemate.global.constant.FileConstant.REVIEW_BASE_PATH;
import static java.io.File.separator;

public class ReviewImageFile extends ImageFile {
    public ReviewImageFile(Review review, MultipartFile file) {
        super(String.join(separator, REVIEW_BASE_PATH, String.valueOf(review.getId())), file);
    }
}
