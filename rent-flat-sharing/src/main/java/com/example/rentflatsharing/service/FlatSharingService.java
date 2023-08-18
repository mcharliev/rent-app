package com.example.rentflatsharing.service;

import com.example.rentflatsharing.model.dto.ApartmentResponseDto;

public interface FlatSharingService {

    ApartmentResponseDto getApartments(String latitude, String longitude);

}
