package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.shelter.file.PetImageFile;
import com.bemate.domain.shelter.repository.PetRepository;
import com.bemate.global.infra.file.service.S3WriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.bemate.global.util.StreamUtil.toStream;

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

    @Transactional
    public void update(Pet pet, List<PetImageFile> imageFiles) {
        var unusedImageNames = getUnusedImages(pet, imageFiles).orElseGet(Collections::emptyList);
        s3WriteService.delete(pet.getImageFolder(), unusedImageNames);

        s3WriteService.upload(imageFiles);

        pet.addImageFiles(imageFiles);
    }

    private Optional<List<String>> getUnusedImages(Pet pet, List<PetImageFile> imageFiles) {
        var requestImageNames = toStream(imageFiles)
                .map(file -> file.getFileName())
                .toList();

        var unusedImageNames = toStream(pet.getImageNamesList())
                .filter(name -> !requestImageNames.contains(name))
                .toList();

        return Optional.ofNullable(unusedImageNames);
    }
}
