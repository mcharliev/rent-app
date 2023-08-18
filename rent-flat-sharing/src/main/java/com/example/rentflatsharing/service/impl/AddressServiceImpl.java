package com.example.rentflatsharing.service.impl;

import com.example.rentflatsharing.exception.AddressNotFoundException;
import com.example.rentflatsharing.model.dto.AddressDto;
import com.example.rentflatsharing.model.entity.Address;
import com.example.rentflatsharing.model.mapper.AddressMapper;
import com.example.rentflatsharing.repository.AddressRepository;
import com.example.rentflatsharing.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AddressDto editAddress(Long apId, AddressDto addressDto) {
        log.info("editAddress method called");
        Address currentAddress = addressRepository.findById(apId).orElseThrow(AddressNotFoundException::new);
        currentAddress.setCity(addressDto.getCity());
        currentAddress.setStreet(addressDto.getStreet());
        currentAddress.setHouseNumber(addressDto.getHouseNumber());
        return addressMapper.addressEntityToAddressDto(currentAddress);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public AddressDto addAddress(AddressDto addressDto) {
        log.info("addAddress method called");
        Address newAddress = addressMapper.addressDtoToAddressEntity(addressDto);
        addressRepository.save(newAddress);
        return addressMapper.addressEntityToAddressDto(newAddress);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAddress(Long apId) {
        log.info("deleteAddress method called");
        addressRepository.deleteById(apId);
    }

    public AddressDto getAddress(Long apId) {
        log.info("getAddress method called");
        Address address = addressRepository.findById(apId).orElseThrow(AddressNotFoundException::new);
        return addressMapper.addressEntityToAddressDto(address);
    }
}
