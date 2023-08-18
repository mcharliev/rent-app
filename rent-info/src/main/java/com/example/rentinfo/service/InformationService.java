package com.example.rentinfo.service;

import com.example.rentinfo.model.entity.Information;
import com.example.rentinfo.exception.InformationDataException;
import com.example.rentinfo.model.dto.InformationDto;
import com.example.rentinfo.repository.InformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class InformationService {

    private final InformationRepository informationDataRepository;

    public InformationDto prepareInfo() {
        Information informationData = informationDataRepository.findById(1L)
                .orElseThrow(InformationDataException::new);
        InformationDto dto = new InformationDto();
        dto.setPathApi(base64Decoder(informationData.getPathApi()));
        dto.setApiKey(base64Decoder(informationData.getApiKey()));
        return dto;
    }

    public String base64Decoder(String string) {
        Base64.Decoder decoder = Base64.getDecoder();
        String decode = new String(decoder.decode(string));
        return decode;
    }

    public String base64Encoder(String string) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(string.getBytes());
    }
}
