package com.bemate.domain.shelter.service;

import com.bemate.domain.shelter.entity.Pet;
import com.bemate.domain.shelter.file.PetImageFile;
import com.bemate.domain.shelter.repository.PetRepository;
import com.bemate.global.infra.file.service.S3WriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bemate.global.util.StreamUtil.toStream;
import static org.springframework.util.CollectionUtils.isEmpty;

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
        delete(pet, imageFiles);

        s3WriteService.upload(imageFiles);

        pet.addImageFiles(imageFiles);
    }

    private void delete(Pet pet, List<PetImageFile> imageFiles) {
        var requestImageNames = toStream(imageFiles)
                .map(file -> file.getFileName())
                .toList();

        var unusedImageNames = toStream(pet.getImageNamesList())
                .filter(name -> !requestImageNames.contains(name))
                .toList();

        if (!isEmpty(unusedImageNames)) {
            s3WriteService.delete(pet.getImageFolder(), unusedImageNames);
        }
    }
}
