package com.example.rentinfo.repository;

import com.example.rentinfo.model.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information,Long> {

}
