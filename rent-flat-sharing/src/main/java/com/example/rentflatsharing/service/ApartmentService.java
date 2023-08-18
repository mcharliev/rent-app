package com.example.rentflatsharing.service;

import com.example.rentflatsharing.model.dto.ApartmentDto;
import com.example.rentflatsharing.model.entity.Apartment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApartmentService {
    public String uploadImage(Long id, MultipartFile image);
    ApartmentDto editApartment(Long apId, ApartmentDto apartmentDto);
    ApartmentDto addApartment(ApartmentDto apartmentDto);
    void deleteApartment(Long apId);
    ApartmentDto getApartment(Long apId);
    ApartmentDto assignApartmentWithAddress(Long apId, Long adId);
    List<Apartment> findApartmentByCity(String city);
}
