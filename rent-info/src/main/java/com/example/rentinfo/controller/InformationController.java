package com.example.rentinfo.controller;

import com.example.rentinfo.model.dto.InformationDto;
import com.example.rentinfo.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InformationController {
    private final InformationService informationService;

    @GetMapping("api-key")
    public ResponseEntity<String> getApiKey(){
        informationService.prepareInfo();
        return ResponseEntity.ok("Json message sent to kafka topic");
    }
}
