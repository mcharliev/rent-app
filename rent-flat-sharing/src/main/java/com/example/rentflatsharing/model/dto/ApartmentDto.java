package com.example.rentflatsharing.model.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ApartmentDto {
    private Integer countRooms;
    private String price;
    private String fullRating;
    private AddressDto addressDto;
}
