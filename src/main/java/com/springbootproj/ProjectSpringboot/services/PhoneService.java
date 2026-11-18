package com.springbootproj.ProjectSpringboot.services;

import com.springbootproj.ProjectSpringboot.model.Phone;
import com.springbootproj.ProjectSpringboot.repo.PhoneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    // ── GET ALL PHONES ────────────────────────────────
    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    // ── GET PHONE BY ID ───────────────────────────────
    public Phone getPhoneById(int id) {
        return phoneRepository.findById(id).orElse(null);
    }

    // ── ADD PHONE ─────────────────────────────────────
    public void addPhone(Phone phone) {
        phoneRepository.save(phone);
    }

    // ── DELETE PHONE ──────────────────────────────────
    public void deletePhone(int id) {
        phoneRepository.deleteById(id);
    }

    // ── CHECK DUPLICATE BY NAME AND BRAND ────────────
    public boolean existsByNameAndBrand(String name, String brand) {
        return phoneRepository.existsByNameAndBrand(name, brand);
    }

    // ── CHECK IF ANY PHONES EXIST IN DB ──────────────
    // Used by DataSeeder to prevent re-seeding on restart
    public boolean hasPhones() {
        return phoneRepository.count() > 0;
    }
}