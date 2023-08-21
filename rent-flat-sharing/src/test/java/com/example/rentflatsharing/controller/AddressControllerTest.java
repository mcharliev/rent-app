package com.example.rentflatsharing.controller;

import com.example.rentflatsharing.exception.AddressNotFoundException;
import com.example.rentflatsharing.model.entity.Address;
import com.example.rentflatsharing.repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AddressRepository repository;

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
    public void addAddressInBdTest() throws Exception {
        Address address = createTestAddress(6L, "Pobeda", "5", "Moscow");
        repository.save(address);
        mvc.perform(post("/address")
                        .content(objectMapper.writeValueAsBytes(address))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<Address> found = repository.findAll();
        assertThat(found).extracting(Address::getCity).contains("Moscow");
    }

    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @Test
    void getAddressFromBdTest() throws Exception {
        Address address = createTestAddress(6L, "Tverskaya", "12", "Orsk");
        repository.save(address);

        mvc.perform(get("/address/{adId}", 6L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Orsk"));

    }

    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @Test
    void getAddressFromBdWhenAddressNotFound() throws Exception {
        Address address = createTestAddress(6L, "Tverskaya", "12", "Orsk");
        repository.save(address);

        mvc.perform(get("/address/{adId}", 10))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException()
                        .getClass()
                        .equals(AddressNotFoundException.class));
    }

    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @Test
    void editAddressTest() throws Exception {
        Address address = createTestAddress(6L, "Tverskaya", "12", "Orsk");
        repository.save(address);

        mvc.perform(patch("/address/{adId}", 6L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTestAddress(6L, "Pobeda", "5", "Moscow")))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Moscow"));
    }


    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    @Test
    void deleteAddressFromBdTest() throws Exception {
        Address address = createTestAddress(6L, "Tverskaya", "12", "Orsk");
        repository.save(address);
        mvc.perform(delete("/address/{adId}", 6L))
                .andExpect(status().isOk());
    }


    private Address createTestAddress(Long id, String street, String houseNumber, String city) {
        Address address = new Address();
        address.setId(id);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setCity(city);
        return address;
    }
}