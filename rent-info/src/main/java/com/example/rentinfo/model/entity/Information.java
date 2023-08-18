package com.example.rentinfo.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "info_data")
@EqualsAndHashCode
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "key_value")
    private String apiKey;

    @Column(name = "path_api")
    private String pathApi;

}
