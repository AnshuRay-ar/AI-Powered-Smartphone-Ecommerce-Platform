package com.springbootproj.ProjectSpringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String brand;
    @Column(length = 2000)
private String description;
    private double price;
    private String imageUrl;

    public Phone() {}

    public Phone(String name, String brand, String description, double price, String imageUrl) {
        this.name        = name;
        this.brand       = brand;
        this.description = description;
        this.price       = price;
        this.imageUrl    = imageUrl;
    }

    public int getId()                         { return id; }
    public void setId(int id)                  { this.id = id; }
    public String getName()                    { return name; }
    public void setName(String name)           { this.name = name; }
    public String getBrand()                   { return brand; }
    public void setBrand(String brand)         { this.brand = brand; }
    public String getDescription()             { return description; }
    public void setDescription(String desc)    { this.description = desc; }
    public double getPrice()                   { return price; }
    public void setPrice(double price)         { this.price = price; }
    public String getImageUrl()                { return imageUrl; }
    public void setImageUrl(String imageUrl)   { this.imageUrl = imageUrl; }
}