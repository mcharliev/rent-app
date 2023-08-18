package com.example.rentflatsharing.service.impl;

import com.example.rentflatsharing.model.entity.Address;
import com.example.rentflatsharing.model.entity.Apartment;
import com.example.rentflatsharing.repository.ApartmentRepository;
import com.example.rentflatsharing.service.ApartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ApartmentServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ApartmentService apartmentService;

    @Test
    void uploadImageTest() {
    }

    @Test
    void editApartmentTest() {
    }

    @Test
    void addApartmentTest() {
    }

    @Test
    void deleteApartmentTest() {
    }

    @Test
    void getApartmentTest() {
    }

    @Test
    void assignApartmentWithAddressTest() {

    }

    @Test
    void findApartmentByCityTest() {
        Apartment apartment1 = new Apartment();
        apartment1.setPrice("50000");
        apartment1.setFullRating("5");
        apartment1.setCountRooms("2");
        Address address1 = new Address();
        address1.setCity("Middle");
        address1.setStreet("Pravda");
        address1.setHouseNumber("5");
        apartment1.setAddress(address1);

        Apartment apartment2 = new Apartment();
        apartment2.setPrice("40000");
        apartment2.setFullRating("4");
        apartment2.setCountRooms("3");
        Address address2 = new Address();
        address2.setCity("Middle");
        address2.setStreet("Pravda");
        address2.setHouseNumber("5");
        apartment2.setAddress(address2);

        when(apartmentRepository.findApartmentByAddress_City("Middle")).thenReturn(List.of(apartment1,apartment2));

        List<Apartment> apartmentList = apartmentRepository.findApartmentByAddress_City("Moscow");


    }
}