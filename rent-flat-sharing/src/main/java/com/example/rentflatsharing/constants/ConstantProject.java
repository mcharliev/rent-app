package com.example.rentflatsharing.constants;

public class ConstantProject {
    public static final String BASE_PATH = "/base_api";
    public static final String GET_FLATS = BASE_PATH + "/get_apartments";

    public static final String EMAIL_SUBJECT = "Выгодное предложение";
    public static final String EMAIL_MESSAGE = "Скидка на все квартиры 15%";

    public static final String MAIL_HOST = "smtp.yandex.ru";
    public static final Integer MAIL_PORT = 465;
    public static final String MAIL_USERNAME = "Mcharliev@yandex.ru";
    public static final String MAIL_PASSWORD = "yrxmfwhwrttxvzwl";

    public static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q={city}&units=metric&appid=0b00e81399248aaef841292f8a8d565a";

    public static final String WEATHER_API_KEY = "0b00e81399248aaef841292f8a8d565a";
    public static final String INFO_SERVICE_URL = "http://localhost:8765/rent-info/api-key";

}
