package com.example.rentflatsharing.model.dto;

import com.example.rentflatsharing.model.Weather;
import lombok.Data;

import java.util.List;

@Data
public class ApartmentResponseDto {

    private List<ApartmentDto> dtoList;
    private Weather weather;
}
