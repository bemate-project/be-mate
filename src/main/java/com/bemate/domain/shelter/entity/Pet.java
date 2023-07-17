package com.bemate.domain.shelter.entity;

import com.bemate.domain.shelter.AdoptionStatus;
import com.bemate.domain.shelter.Gender;
import com.bemate.domain.shelter.HealthStatus;
import com.bemate.domain.user.entity.BaseEntity;
import com.bemate.global.infra.file.ImageFile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pet extends BaseEntity {
    @Id
    @Column(name = "pet_key")
    private String id;
    private String species;
    private String kind;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String characteristics;
    @Enumerated(EnumType.STRING)
    private HealthStatus healthStatus;
    private String imageFiles;
    @Enumerated(EnumType.STRING)
    private AdoptionStatus adoptionStatus;

    public void addImageFiles(List<? extends ImageFile> imageFiles) {
        var fileNames = getFileNames(imageFiles).orElse("");
        this.imageFiles = fileNames;
    }

    private Optional<String> getFileNames(List<? extends ImageFile> imageFiles) {
        return Optional.ofNullable(imageFiles
                .stream()
                .map(imageFile -> imageFile.getFileName())
                .collect(joining("||"))
        );
    }
}
