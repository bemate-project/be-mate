package com.bemate.global.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.io.File.separator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileConstant {
    public static final String TEMP_FOLDER_PATH =
            String.join(separator, System.getProperty("user.dir"), "tmp");
    public static final String PET_BASE_PATH = "pet";
    public static final String REVIEW_BASE_PATH = "review";
}
