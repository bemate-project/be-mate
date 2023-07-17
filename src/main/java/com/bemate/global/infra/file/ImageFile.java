package com.bemate.global.infra.file;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Getter
public abstract class ImageFile {
    private final String base;
    private final String fileName;
    private final byte[] bytes;

    protected ImageFile(String base, MultipartFile file) {
        this.base = base;
        this.fileName = normalizeFileName(file.getOriginalFilename());
        try {
            this.bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String normalizeFileName(String fileName) {
        return URLDecoder.decode(fileName, StandardCharsets.UTF_8);
    }
}
