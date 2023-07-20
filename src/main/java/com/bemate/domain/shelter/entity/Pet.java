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
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.bemate.global.constant.ServerConstant.SERVER_HOST;
import static java.util.stream.Collectors.joining;
import static org.springframework.util.CollectionUtils.isEmpty;

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
    private String imageFolder;
    private String imageFiles;
    @Enumerated(EnumType.STRING)
    private AdoptionStatus adoptionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_no")
    private Shelter shelter;

    public void addImageFiles(List<? extends ImageFile> imageFiles) {
        if (!isEmpty(imageFiles)) {
            this.imageFolder = imageFiles.get(0).getBase();
            this.imageFiles = getFileNames(imageFiles);
        } else {
            this.imageFolder = "";
            this.imageFiles = "";
        }
    }

    private String getFileNames(List<? extends ImageFile> imageFiles) {
        return imageFiles
                .stream()
                .map(imageFile -> imageFile.getFileName())
                .collect(joining("||"));
    }

    public List<String> getImages() {
        if (!StringUtils.hasText(imageFiles)) {
            return Collections.emptyList();
        }

        return Arrays.stream(this.imageFiles.split("\\|\\|"))
                .map(file -> String.format("%s/%s/%s", SERVER_HOST, this.imageFolder, file))
                .toList();
    }
}
