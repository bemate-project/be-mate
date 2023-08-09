package com.bemate.global.infra.file.service;

import com.bemate.global.infra.file.ImageFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3WriteService {
    private final S3Uploader s3Uploader;

    public void upload(ImageFile imageFile) {
        try {
            s3Uploader.upload(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void upload(List<? extends ImageFile> imageFiles) {
        for (var imageFile : imageFiles) {
            upload(imageFile);
        }
    }
}
