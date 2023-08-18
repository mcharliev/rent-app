package com.example.rentinfo.service;

import com.example.rentinfo.exception.InformationDataException;
import com.example.rentinfo.model.dto.InformationDto;
import com.example.rentinfo.model.entity.Information;
import com.example.rentinfo.repository.InformationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class InformationServiceTest {
    @Autowired
    private InformationService informationService;
    @Autowired
    private InformationRepository repository;


    @Test
    void prepareInfo() {
        Information infoFromBd = getInfoFromBd();

        InformationDto dto = new InformationDto();
        dto.setApiKey(base64Decoder(infoFromBd.getApiKey()));
        dto.setPathApi(base64Decoder(infoFromBd.getPathApi()));

        InformationDto informationDto = informationService.prepareInfo();

        assertEquals(dto, informationDto);
        assertDoesNotThrow(InformationDataException::new);
    }

    private Information getInfoFromBd() throws InformationDataException {
        return repository.findById(1L).orElseThrow(InformationDataException::new);
    }

    public String base64Decoder(String string) {
        Base64.Decoder decoder = Base64.getDecoder();
        String decode = new String(decoder.decode(string));
        return decode;
    }
}