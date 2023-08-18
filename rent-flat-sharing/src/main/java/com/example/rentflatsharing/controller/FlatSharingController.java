package com.example.rentflatsharing.controller;

import com.example.rentflatsharing.model.dto.ApartmentResponseDto;
import com.example.rentflatsharing.service.FlatSharingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.rentflatsharing.constants.ConstantProject.GET_FLATS;

@RequiredArgsConstructor
@RestController
public class FlatSharingController {

    private final FlatSharingService flatSharingService;

    @GetMapping(GET_FLATS)
    public ApartmentResponseDto findApartmentsByLocation(@RequestParam String latitude,
                                                         @RequestParam String longitude) {
        return flatSharingService.getApartments(latitude, longitude);
    }

}
