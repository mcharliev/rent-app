package com.example.rentinfo.kafka;


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
public class JsonKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
    private final KafkaTemplate<String, Information> kafkaTemplate;

    public void sendMessage(Information info) {
        LOGGER.info(String.format("message sent -> %s", info.toString()));
        Message<Information> message = MessageBuilder
                .withPayload(info)
                .setHeader(KafkaHeaders.TOPIC, "rent-topic")
                .build();
        kafkaTemplate.send(message);
    }

}
