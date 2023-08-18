package com.example.rentflatsharing.service.impl;

import com.example.rentflatsharing.model.Weather;
import com.example.rentflatsharing.service.WeatherService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.example.rentflatsharing.constants.ConstantProject.WEATHER_URL;

@Service
public class WeatherServiceImp implements WeatherService {

    public Weather getInfoAboutWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(WEATHER_URL,
                        HttpMethod.GET,
                        new HttpEntity<>(HttpHeaders.EMPTY),
                        Weather.class, city)
                .getBody();
    }

}
