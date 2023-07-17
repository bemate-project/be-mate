package com.bemate.domain.shelter.file;

import com.bemate.domain.shelter.entity.Pet;
import com.bemate.global.infra.file.ImageFile;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import static com.bemate.global.constant.FileConstant.PET_BASE_PATH;
import static java.io.File.separator;

@Getter
public class PetImageFile extends ImageFile {
    public PetImageFile(Pet pet, MultipartFile file) {
        super(String.join(separator, PET_BASE_PATH, pet.getId()), file);
    }
}
