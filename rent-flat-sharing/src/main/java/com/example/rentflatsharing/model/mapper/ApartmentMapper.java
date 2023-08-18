package com.example.rentflatsharing.model.mapper;

import com.example.rentflatsharing.model.Weather;
import com.example.rentflatsharing.model.dto.AddressDto;
import com.example.rentflatsharing.model.dto.ApartmentDto;
import com.example.rentflatsharing.model.dto.ApartmentResponseDto;
import com.example.rentflatsharing.model.entity.Address;
import com.example.rentflatsharing.model.entity.Apartment;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApartmentMapper {

    private final Logger log = LoggerFactory.getLogger(ApartmentMapper.class);
    private final AddressMapper addressMapper;

    public ApartmentDto apartmentEntityToApartmentDto(Apartment entity) {
        log.info("apartmentEntityToApartmentDto method called");
        ApartmentDto dto = new ApartmentDto();
        dto.setPrice(entity.getPrice());
        dto.setFullRating(entity.getFullRating());
        dto.setCountRooms(Integer.valueOf(entity.getCountRooms()));
        Address address = entity.getAddress();
        if (address != null) {
            dto.setAddressDto(addressMapper.addressEntityToAddressDto(address));
        }
        return dto;
    }

    public Apartment apartmentDtoToApartmentEntity(ApartmentDto dto) {
        log.info("apartmentDtoToApartmentEntity method called");
        Apartment entity = new Apartment();
        entity.setCountRooms(String.valueOf(dto.getCountRooms()));
        entity.setFullRating(dto.getFullRating());
        entity.setPrice(dto.getPrice());
        AddressDto addressDto = dto.getAddressDto();
        if (addressDto != null) {
            entity.setAddress(addressMapper.addressDtoToAddressEntity(addressDto));
        }
        return entity;
    }

    public List<ApartmentDto> apartmentListToApartmentDtoList(List<Apartment> entityList) {
        log.info("addressListToAddressDtoList method called");
        return entityList.stream()
                .map(this::apartmentEntityToApartmentDto)
                .collect(Collectors.toList());
    }

    public ApartmentResponseDto mapToApartmentResponse(List<ApartmentDto> list, Weather weather) {
        ApartmentResponseDto apartmentResponseDto = new ApartmentResponseDto();
        apartmentResponseDto.setDtoList(list);
        apartmentResponseDto.setWeather(weather);
        return apartmentResponseDto;
    }

}
