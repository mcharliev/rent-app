package com.example.rentflatsharing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class Components {

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "town")
    private String town;

    @JsonProperty(value = "state")
    private String state;

}
