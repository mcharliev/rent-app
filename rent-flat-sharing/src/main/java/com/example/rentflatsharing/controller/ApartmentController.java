package com.example.rentflatsharing.controller;

import com.example.rentflatsharing.model.dto.ApartmentDto;
import com.example.rentflatsharing.service.impl.ApartmentServiceImpl;
import com.example.rentflatsharing.service.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    private final ApartmentServiceImpl apartmentService;
    private final ImageServiceImpl imageService;

    @PostMapping("{apId}/image")
    public ResponseEntity<String> uploadImage(@PathVariable(required = false) Long apId,
                                              @RequestParam MultipartFile file) {
        return ResponseEntity.ok().body(apartmentService.uploadImage(apId, file));
    }

    @PatchMapping("/{apId}")
    public ResponseEntity<ApartmentDto> editApartment(@PathVariable("apId") Long apId,
                                                      @RequestPart ApartmentDto apartmentDto) {
        return ResponseEntity.ok(apartmentService.editApartment(apId, apartmentDto));
    }

    @PostMapping
    ResponseEntity<ApartmentDto> addApartment(@RequestBody ApartmentDto apartmentDto) {
        return ResponseEntity.ok(apartmentService.addApartment(apartmentDto));
    }


    @DeleteMapping("/{apId}")
    public void deleteApartment(@PathVariable Long apId) {
        apartmentService.deleteApartment(apId);
    }

    @GetMapping("/{apId}")
    public ResponseEntity<ApartmentDto> getApartment(@PathVariable Long apId) {
        return ResponseEntity.ok(apartmentService.getApartment(apId));
    }

    @PutMapping("/{apId}/{adId}")
    ResponseEntity<ApartmentDto> assignApartmentWithAddress(@PathVariable(required = false) Long apId,
                                                            @PathVariable(required = false) Long adId) {
        return ResponseEntity.ok(apartmentService.assignApartmentWithAddress(apId, adId));
    }

    @GetMapping(value = "/{apId}/image", produces = {MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE, "image/*"})
    public ResponseEntity<byte[]> getImage(@PathVariable Long apId) {
        return ResponseEntity.ok(imageService.getImage(apId));
    }
}
