package com.bemate.domain.review.entity;

import com.bemate.domain.shelter.entity.Shelter;
import com.bemate.global.infra.file.ImageFile;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static java.util.stream.Collectors.joining;
import static org.springframework.util.CollectionUtils.isEmpty;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_no")
    private Long id;
    private String title;
    private String content;
    private String imageFolder;
    private String imageFiles;

    @ManyToOne(fetch = LAZY)
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
}
