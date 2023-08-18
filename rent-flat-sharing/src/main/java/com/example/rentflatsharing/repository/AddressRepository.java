package com.example.rentflatsharing.repository;

import com.example.rentflatsharing.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    List<Address> findAllByCity(String city);

    @Query(nativeQuery = true,value = "SELECT * FROM address_info where city = :city")
    List<Address> getAllByCity(String city);

    @Query(value = "SELECT a FROM Address a where a.city = :city ")
    List<Address> getAllFlatsByCity(String city);


}
