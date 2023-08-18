package com.example.rentflatsharing.repository;

import com.example.rentflatsharing.model.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<Apartment,Long> {

    List<Apartment> findApartmentByAddress_City(String city);
}
