package com.bookstore.eagle.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FileServiceImpl implements FileService {

    private static final String PATH_USER = System.getProperty("user.home") + File.separator + "quarkus"
        + File.separator + "images" + File.separator + "user" + File.separator;
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    private static final List<String> SUPPORTED_IMAGE_TYPES = Arrays.asList("image/jpg", "image/jpg", "image/gif");

    @Override
    public String saveProductImage(byte[] image, String imageName) throws IOException {
        String mimeType = Files.probeContentType(new File(imageName).toPath());
        if (!SUPPORTED_IMAGE_TYPES.contains(mimeType)) {
            throw new IOException("Unsupported image type.");
        }

        if (image.length > MAX_FILE_SIZE) {
            throw new IOException("File size exceeds the maximum limit.");
        }

        File directory = new File(PATH_USER);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID() + "." + mimeType.substring(mimeType.lastIndexOf("/") + 1);

        String path = PATH_USER + fileName;
        File file = new File(path);
        if (file.exists()) {
            throw new IOException("The generated image name is duplicated.");
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(image);
            fos.flush();
        }

        return fileName;
    }

    @Override
    public File download(String fileName) {
        return new File(PATH_USER + fileName);        
    }
    
}
