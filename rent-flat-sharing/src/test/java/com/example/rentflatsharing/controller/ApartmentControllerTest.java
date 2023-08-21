package com.example.rentflatsharing.controller;

import com.example.rentflatsharing.exception.AddressNotFoundException;
import com.example.rentflatsharing.exception.ApartmentNotFoundException;
import com.example.rentflatsharing.model.entity.Address;
import com.example.rentflatsharing.model.entity.Apartment;
import com.example.rentflatsharing.repository.AddressRepository;
import com.example.rentflatsharing.repository.ApartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApartmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ApartmentRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;


    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void resetDb() {
        repository.deleteAll();
    }



    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @Test
    void editApartment() throws Exception {
        Apartment apartment = createTestApartment("5", "50000", "7");
        repository.save(apartment);

        mvc.perform(patch("/apartment/{apId}", 6L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTestApartment( "3", "30000", "6")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("30000"));

    }

    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @Test
    void addApartmentInBdTest() throws Exception {
        Apartment apartment = createTestApartment("5", "50000", "7");
        repository.save(apartment);

        mvc.perform(post("/apartment")
                        .content(objectMapper.writeValueAsBytes(apartment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Apartment> found = repository.findAll();
        assertThat(found).extracting(Apartment::getPrice).contains("50000");
    }


    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @Test
    void deleteApartmentFromBdTest() throws Exception {
        Apartment apartment = createTestApartment("5", "50000", "7");
        repository.save(apartment);
        mvc.perform(delete("/apartment/{apId}", 6L))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @Test
    void getApartment() throws Exception {
        Apartment apartment = createTestApartment("5", "50000", "7");
        Apartment savedApartment = repository.save(apartment);
        Long id = savedApartment.getId();

        mvc.perform(get("/apartment/{apId}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("50000"));
    }

    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @Test
    void getApartmentsFromBdWhenApartmentNotFound() throws Exception {
        Apartment apartment = createTestApartment("5", "50000", "7");
        repository.save(apartment);

        mvc.perform(get("/apartment/{apId}", 20L))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException()
                        .getClass()
                        .equals(ApartmentNotFoundException.class));
    }


    private Apartment createTestApartment(String countRoom, String price, String rating) {
        Apartment apartment = new Apartment();
        apartment.setPrice(price);
        apartment.setCountRooms(countRoom);
        apartment.setFullRating(rating);
        apartment.setLocalDateTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        return repository.saveAndFlush(apartment);
    }
}