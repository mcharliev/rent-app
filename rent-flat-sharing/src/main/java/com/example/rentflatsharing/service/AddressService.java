package com.example.rentflatsharing.service;

import com.example.rentflatsharing.model.dto.AddressDto;

public interface AddressService {
    AddressDto editAddress(Long apId, AddressDto addressDto);
    AddressDto addAddress(AddressDto addressDto);
    void deleteAddress(Long apId);
    AddressDto getAddress(Long apId);
}
