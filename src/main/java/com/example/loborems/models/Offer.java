package com.example.loborems.models;

import javax.persistence.*;

@Entity
@Table(name = "offer")
public class Offer {
    public enum PropertyType {
        HOUSE, APARTMENT, CONDO
    }

    public enum OfferType {
        SALE, RENT
    }

    public enum Status {
        PENDING, ACCEPTED, REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String clientName;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private OfferType offerType;

    private double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Default constructor
    public Offer() {
    }

    // Parameterized constructor
    public Offer(String clientName, PropertyType propertyType, OfferType offerType, double price, Status status) {
        this.clientName = clientName;
        this.propertyType = propertyType;
        this.offerType = offerType;
        this.price = price;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
