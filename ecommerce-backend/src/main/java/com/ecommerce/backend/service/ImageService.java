package com.ecommerce.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    private final String UPLOAD_DIR=System.getProperty("user.dir")+ "/src/main/resources/static/uploads/";

    public String uploadImage(MultipartFile file) throws IOException {
        File directory =new File(UPLOAD_DIR);

        if(!directory.exists()){
            directory.mkdirs();
        }

        String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();

        File destination =new File(UPLOAD_DIR+fileName);
        file.transferTo(destination);
        return "/uploads/"+fileName;
    }
}
