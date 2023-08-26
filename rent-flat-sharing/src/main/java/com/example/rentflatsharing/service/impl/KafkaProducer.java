package com.example.rentflatsharing.service.impl;

import com.example.rentinfo.model.entity.Information;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent %s",message));
        kafkaTemplate.send("rent-string",message);
    }
}
