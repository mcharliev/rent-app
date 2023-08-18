package com.example.rentflatsharing.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    byte[] getImage(Long apId);
    String uploadImage(Long apId, MultipartFile file);
    void deleteImage(Long apId);
}
