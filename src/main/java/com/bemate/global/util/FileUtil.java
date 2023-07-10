package com.bemate.global.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    public static Boolean isExistFolder(String folderPath) {
        var folder = new File(folderPath);
        return folder.exists();
    }

    public static void createFolder(String folderPath) {
        var folder = new File(folderPath);
        folder.mkdirs();
    }

    public static File saveMultipartFileInLocal(byte[] bytes, String fileURL)
            throws IOException {
        return Files.write(Path.of(fileURL), bytes).toFile();
    }

}
