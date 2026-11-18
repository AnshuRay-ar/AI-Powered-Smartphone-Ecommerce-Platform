package com.springbootproj.ProjectSpringboot.repo;

import com.springbootproj.ProjectSpringboot.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    // ── CHECK IF PHONE ALREADY EXISTS BY NAME AND BRAND ──
    boolean existsByNameAndBrand(String name, String brand);
}