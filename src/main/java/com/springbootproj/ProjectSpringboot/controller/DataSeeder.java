package com.springbootproj.ProjectSpringboot.controller;

import com.springbootproj.ProjectSpringboot.model.Phone;
import com.springbootproj.ProjectSpringboot.services.PhoneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private PhoneService phoneService;

    @Override
    public void run(String... args) {

        // ── ONLY SEED IF DB IS EMPTY ──────────────────
        // Prevents duplicate records on every app restart
        if (!phoneService.hasPhones()) {

            phoneService.addPhone(new Phone(
                    "iPhone 15",
                    "Apple",
                    "Apple Flagship Smartphone",
                    79999,
                    "/images/iphone.png"
            ));

            phoneService.addPhone(new Phone(
                    "Samsung S24",
                    "Samsung",
                    "Powerful Android Experience",
                    69999,
                    "/images/samsung.jpg"
            ));

            phoneService.addPhone(new Phone(
                    "OnePlus 12",
                    "OnePlus",
                    "Fast & Smooth Performance",
                    59999,
                    "/images/oneplus.png"
            ));

            System.out.println(
                    "✅ Seeded 3 default phones into database."
            );

        } else {

            System.out.println(
                    "ℹ️ Database already has phones. Skipping seed."
            );
        }
    }
}