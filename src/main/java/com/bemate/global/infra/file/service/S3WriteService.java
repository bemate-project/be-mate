package com.bemate.global.infra.file.service;

import com.bemate.domain.shelter.file.PetImageFile;
import com.bemate.global.infra.file.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3WriteService {
    private final S3Uploader s3Uploader;

    public void upload(List<PetImageFile> petImageFile) {
        for (var imageFile : petImageFile) {

        }
        try {
            s3Uploader.upload(petImageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
