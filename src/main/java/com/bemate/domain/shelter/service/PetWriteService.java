package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.shelter.file.PetImageFile;
import com.bemate.global.infra.file.service.S3WriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetWriteService {

    private final S3WriteService s3WriteService;

    public void save(Pet pet, List<PetImageFile> petImageFiles) throws IOException {
        s3WriteService.upload(petImageFiles);
    }
}
