package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.shelter.file.PetImageFile;
import com.bemate.domain.shelter.repository.PetRepository;
import com.bemate.global.infra.file.service.S3WriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetWriteService {

    private final S3WriteService s3WriteService;
    private final PetRepository petRepository;

    public Pet register(Pet pet, List<PetImageFile> imageFiles) {
        s3WriteService.upload(imageFiles);

        pet.addImageFiles(imageFiles);

        return petRepository.save(pet);
    }
}
