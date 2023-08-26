package com.example.rentinfo.kafka;


import com.example.rentinfo.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final InformationService informationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "rent-string",groupId = "myGroup")
    public void consume(String message){
        if (message.equals("geoCodeApi")){
            informationService.prepareInfo();
        }
        LOGGER.info(String.format("Message received -> %s",message));
    }
}
