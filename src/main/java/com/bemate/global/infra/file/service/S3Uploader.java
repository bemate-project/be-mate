package com.bemate.global.infra.file.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bemate.global.infra.file.ImageFile;
import com.bemate.global.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.bemate.global.constant.FileConstant.TEMP_FOLDER_PATH;
import static java.io.File.separator;

@Component
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public void upload(ImageFile imageFile) throws IOException {
        var tempFolder = String.join(separator, TEMP_FOLDER_PATH, imageFile.getBase());

        if (!FileUtil.isExistFolder(tempFolder)) {
            FileUtil.createFolder(tempFolder);
        }

        var localFile = FileUtil.saveMultipartFileInLocal(imageFile.getBytes(),
                String.join(separator, tempFolder, imageFile.getFileName()));

        amazonS3Client.putObject(new PutObjectRequest(
                bucket,
                String.join(separator, imageFile.getBase(), imageFile.getFileName()),
                localFile));

        localFile.delete();
    }

}
