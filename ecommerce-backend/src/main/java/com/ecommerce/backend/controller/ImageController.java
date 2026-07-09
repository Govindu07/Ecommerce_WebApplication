package com.ecommerce.backend.controller;

import com.ecommerce.backend.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/uploads")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file){
        try{
            String path=imageService.uploadImage(file);
            return ResponseEntity.ok(path);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
