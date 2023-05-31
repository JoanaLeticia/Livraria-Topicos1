package com.bookstore.eagle.service;

import java.io.File;
import java.io.IOException;

public interface FileService {
    
    String saveProductImage(byte[] image, String imageName) throws IOException;

    File download(String fileName);

}
