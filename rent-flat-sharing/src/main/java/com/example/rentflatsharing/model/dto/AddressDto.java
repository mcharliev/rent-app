package com.example.rentflatsharing.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressDto {
    private String city;
    private String street;
    private String houseNumber;
}
