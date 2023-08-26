package com.example.rentflatsharing.service.impl;


import com.example.rentinfo.model.entity.Information;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Data
@Service
@RequiredArgsConstructor
public class JsonKafkaConsumer {

    private HashMap<String,Information> infoMap = new HashMap();

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "rent-topic", groupId = "myGroup")
    public void consumer(Information info) {
        infoMap.put("geoCodeApi",info);
        LOGGER.info(String.format("Json message recieved -> %s", info.toString()));
    }

}
