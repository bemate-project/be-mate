package com.bemate.global.infra.file.service;

import com.bemate.global.exception.S3UploadFailException;
import com.bemate.global.infra.file.ImageFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static java.io.File.separator;

@Service
@RequiredArgsConstructor
public class S3WriteService {
    private final S3Uploader s3Uploader;

    public void upload(ImageFile imageFile) {
        try {
            s3Uploader.upload(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void upload(List<? extends ImageFile> imageFiles) {
        for (var imageFile : imageFiles) {
            if(imageFile.getFileName().startsWith("lp-media")) {
                continue;
            }
            upload(imageFile);
        }
    }

    public void delete(String base) {
        try {
            s3Uploader.delete(base);
        } catch (IOException e) {
            throw new S3UploadFailException(base, e);
        }
    }

    public void delete(String base, List<String> fileNames) {
        for (var fileName : fileNames) {
            if(fileName.startsWith("lp-media")) {
                continue;
            }
            delete(String.join(separator, base, fileName));
        }
    }
}
