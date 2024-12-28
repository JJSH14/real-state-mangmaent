package com.example.loborems.models;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "property_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String location;
    private double size;
    private double price;
    private String features;
    private String status;

    // Common getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public double getSize() { return size; }
    public void setSize(double size) { this.size = size; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
