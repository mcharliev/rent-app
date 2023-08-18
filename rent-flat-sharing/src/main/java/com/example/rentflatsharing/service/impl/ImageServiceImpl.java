package com.example.rentflatsharing.service.impl;

import com.example.rentflatsharing.exception.ApartmentNotFoundException;
import com.example.rentflatsharing.model.entity.Apartment;
import com.example.rentflatsharing.repository.ApartmentRepository;
import com.example.rentflatsharing.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ApartmentRepository apartmentRepository;

    @Value("${image.dir.path}")
    private String imageDirPath;

    @Value("${image.file.name.postfix}")
    private String fileNamePostfix;

    @SneakyThrows
    public byte[] getImage(Long apId) {
        Apartment apartment = apartmentRepository.findById(apId).orElseThrow(ApartmentNotFoundException::new);
        String fileName = apartment.getImage();
        return Files.readAllBytes(Path.of(imageDirPath, fileName + fileNamePostfix));
    }

    @SneakyThrows
    public String uploadImage(Long apId, MultipartFile file) {
        Path filePath = Path.of(imageDirPath, apId + fileNamePostfix);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        file.transferTo(filePath);
        return apId.toString();
    }

    @SneakyThrows
    public void deleteImage(Long apId) {
        Files.deleteIfExists(Path.of(imageDirPath, apId + fileNamePostfix));
    }
}
