package com.example.rentflatsharing.service.impl;

import com.example.rentflatsharing.exception.AddressNotFoundException;
import com.example.rentflatsharing.exception.ApartmentNotFoundException;
import com.example.rentflatsharing.model.dto.ApartmentDto;
import com.example.rentflatsharing.model.entity.Address;
import com.example.rentflatsharing.model.entity.Apartment;
import com.example.rentflatsharing.model.mapper.ApartmentMapper;
import com.example.rentflatsharing.repository.AddressRepository;
import com.example.rentflatsharing.repository.ApartmentRepository;
import com.example.rentflatsharing.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ImageServiceImpl imageService;
    private final ApartmentMapper apartmentMapper;
    private final AddressRepository addressRepository;
    private final Logger log = LoggerFactory.getLogger(ApartmentServiceImpl.class);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public String uploadImage(Long id, MultipartFile image) {
        log.info("uploadImage image method called");
        Apartment apartment = apartmentRepository.findById(id).orElseThrow(ApartmentNotFoundException::new);
        apartment.setImage(imageService.uploadImage(id, image));
        apartmentRepository.save(apartment);
        return "Image upload";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApartmentDto editApartment(Long apId, ApartmentDto apartmentDto) {
        log.info("editApartment method called");
        Apartment currentApartment = apartmentRepository.findById(apId).orElseThrow(ApartmentNotFoundException::new);
        currentApartment.setPrice(apartmentDto.getPrice());
        currentApartment.setFullRating(apartmentDto.getFullRating());
        currentApartment.setCountRooms(String.valueOf(apartmentDto.getCountRooms()));
        currentApartment.setLocalDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        apartmentRepository.save(currentApartment);
        return apartmentMapper.apartmentEntityToApartmentDto(currentApartment);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApartmentDto addApartment(ApartmentDto apartmentDto) {
        log.info("addApartment method called");
        Apartment newApartment = apartmentMapper.apartmentDtoToApartmentEntity(apartmentDto);
        Address address = newApartment.getAddress();
        if (address != null) {
            addressRepository.save(address);
        }
        newApartment.setLocalDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        apartmentRepository.save(newApartment);
        return apartmentMapper.apartmentEntityToApartmentDto(newApartment);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteApartment(Long apId) {
        log.info("deleteApartment method called");
        imageService.deleteImage(apId);
        apartmentRepository.deleteById(apId);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ApartmentDto getApartment(Long apId) {
        log.info("getApartment method called");
        Apartment apartment = apartmentRepository.findById(apId).orElseThrow(ApartmentNotFoundException::new);
        return apartmentMapper.apartmentEntityToApartmentDto(apartment);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApartmentDto assignApartmentWithAddress(Long apId, Long adId) {
        log.info("assignApartmentWithAddress method called");
        Apartment apartment = apartmentRepository.findById(apId).orElseThrow(ApartmentNotFoundException::new);
        Address address = addressRepository.findById(adId).orElseThrow(AddressNotFoundException::new);
        apartment.setAddress(address);
        apartmentRepository.save(apartment);
        return apartmentMapper.apartmentEntityToApartmentDto(apartment);
    }

    public List<Apartment> findApartmentByCity(String city) {
        return apartmentRepository.findApartmentByAddress_City(city);
    }
}
