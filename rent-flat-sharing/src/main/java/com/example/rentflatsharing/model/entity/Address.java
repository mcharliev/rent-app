package com.example.rentflatsharing.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@Table(name = "address_info")
public class Address {

    @Id
    @SequenceGenerator(name = "address_infoSequence", sequenceName = "address_info_sequence", allocationSize = 1, initialValue = 6)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_infoSequence")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @Column(name = "street", nullable = false)
    private String street;

    @NotNull
    @Column(name = "house_number", nullable = false)
    private String houseNumber;
}
