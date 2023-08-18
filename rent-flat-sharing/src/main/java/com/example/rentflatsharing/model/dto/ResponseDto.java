package com.example.rentflatsharing.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
@Data
public class ResponseDto {

    @JsonProperty(value = "results")
    private List<ResultDto> resultObject;
}
