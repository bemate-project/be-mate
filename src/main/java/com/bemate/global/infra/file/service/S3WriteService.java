package com.bemate.global.infra.file.service;

import com.bemate.domain.shelter.file.PetImageFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3WriteService {
    private final S3Uploader s3Uploader;

    public void upload(PetImageFile petImageFile) {
        try {
            s3Uploader.upload(petImageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void upload(List<PetImageFile> petImageFiles) {
        for (var petImageFile : petImageFiles) {
            upload(petImageFile);
        }
    }
}
