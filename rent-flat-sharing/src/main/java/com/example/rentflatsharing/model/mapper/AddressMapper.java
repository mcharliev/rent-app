package com.example.rentflatsharing.model.mapper;

import com.example.rentflatsharing.model.dto.AddressDto;
import com.example.rentflatsharing.model.entity.Address;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapper {
    private final Logger log = LoggerFactory.getLogger(AddressMapper.class);

    public AddressDto addressEntityToAddressDto(Address entity) {
        log.info("addressEntityToAddressDto method called");
        AddressDto dto = new AddressDto();
        dto.setCity(entity.getCity());
        dto.setHouseNumber(entity.getHouseNumber());
        dto.setStreet(entity.getStreet());
        return dto;
    }

    public Address addressDtoToAddressEntity(AddressDto dto) {
        log.info("addressDtoToAddressEntity method called");
        Address entity = new Address();
        entity.setCity(dto.getCity());
        entity.setHouseNumber(dto.getHouseNumber());
        entity.setStreet(dto.getStreet());
        return entity;
    }
}
