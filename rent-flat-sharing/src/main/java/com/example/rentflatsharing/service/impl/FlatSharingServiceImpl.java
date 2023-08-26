package com.example.rentflatsharing.service.impl;

import com.example.rentflatsharing.exception.ParseJsonToObjectException;
import com.example.rentflatsharing.model.Components;
import com.example.rentflatsharing.model.InfoData;
import com.example.rentflatsharing.model.Weather;
import com.example.rentflatsharing.model.dto.ApartmentDto;
import com.example.rentflatsharing.model.dto.ApartmentResponseDto;
import com.example.rentflatsharing.model.dto.ResponseDto;
import com.example.rentflatsharing.model.dto.ResultDto;
import com.example.rentflatsharing.model.entity.Apartment;
import com.example.rentflatsharing.model.mapper.ApartmentMapper;
import com.example.rentflatsharing.service.FlatSharingService;
import com.example.rentinfo.kafka.JsonKafkaProducer;
import com.example.rentinfo.model.entity.Information;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.rentflatsharing.constants.ConstantProject.INFO_SERVICE_URL;

@Service
@RequiredArgsConstructor
public class FlatSharingServiceImpl implements FlatSharingService {
    private final ApartmentMapper apartmentMapper;
    private final ApartmentServiceImpl apartmentService;
    private final JsonKafkaConsumer kafkaConsumer;
    private final KafkaProducer kafkaProducer;
    private final WeatherServiceImp weatherServiceImp;
    private final Logger log = LoggerFactory.getLogger(FlatSharingServiceImpl.class);

    @Override
    public ApartmentResponseDto getApartments(String latitude, String longitude) {
        kafkaProducer.sendMessage("geoCodeApi");
        String fullInfoByPoint = getFullInfoByPoint(latitude, longitude);
        log.info("getFullInfoByPoint <- geocode-service");
        String city = getParseResultToObject(fullInfoByPoint);
        Weather weatherInfo = weatherServiceImp.getInfoAboutWeather(city);
        List<Apartment> entityList = apartmentService.findApartmentByCity(city);
        List<ApartmentDto> dtoList = apartmentMapper.apartmentListToApartmentDtoList(entityList);
        return apartmentMapper.mapToApartmentResponse(dtoList, weatherInfo);
    }

    private String getFullInfoByPoint(String latitude, String longitude) {
        Information geocodeServiceApi = kafkaConsumer.getInfoMap().get("geoCodeApi");
        log.info("getFullInfoByPoint -> geocode-service");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(String.format(geocodeServiceApi.getPathApi(), latitude,
                                longitude, geocodeServiceApi.getApiKey()),
                        HttpMethod.GET,
                        new HttpEntity<>(null),
                        String.class)
                .getBody();
    }

//    private InfoData getGeocodeServiceApi() {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.exchange(INFO_SERVICE_URL,
//                        HttpMethod.GET,
//                        new HttpEntity<>(HttpHeaders.EMPTY),
//                        InfoData.class)
//                .getBody();
//    }
    
    private String getParseResultToObject(String fullInfo) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ResponseDto responseDto = objectMapper.readValue(fullInfo, ResponseDto.class);
            List<ResultDto> resultObject = responseDto.getResultObject();
            ResultDto resultDto = resultObject.get(0);
            Components components = resultDto.getComponents();
            if (components.getCity() != null) {
                return components.getCity();
            } else if (components.getTown() != null) {
                return components.getTown();
            } else {
                return components.getState();
            }
        } catch (JsonProcessingException e) {
            throw new ParseJsonToObjectException();
        }
    }
}
