package com.example.rentflatsharing.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "apartment_info")
public class Apartment {

    @Id
    @SequenceGenerator(name = "apartment_infoSequence", sequenceName = "apartment_info_sequence", allocationSize = 1,initialValue = 6 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartment_infoSequence")
    @Column(name = "id")
    private Long id;


    @Column(name = "count_rooms",nullable = false)
    private String countRooms;


    @Column(name = "price",nullable = false)
    private String price;


    @Column(name = "full_rating",nullable = false)
    private String fullRating;


    @Column(name = "time_reg_lot",nullable = false)
    private LocalDateTime localDateTime;

    @Column(name = "image")
    private String image;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
