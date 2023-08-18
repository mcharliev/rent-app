package com.example.rentflatsharing.model.dto;

import com.example.rentflatsharing.model.Components;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class ResultDto {

    @JsonProperty(value = "components")
    private Components components;
}
