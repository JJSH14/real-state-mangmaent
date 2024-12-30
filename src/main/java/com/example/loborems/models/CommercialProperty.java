package com.example.loborems.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Commercial")
public class CommercialProperty extends Property {
    private int numberOfFloors;
    private int parkingSpaces;
    @Override
    public void setType(String type) {
        // Set the property type explicitly if needed
        // In this case, it's already set by the DiscriminatorValue annotation
    }
    public int getNumberOfFloors() { return numberOfFloors; }
    public void setNumberOfFloors(int numberOfFloors) { this.numberOfFloors = numberOfFloors; }
    public int getParkingSpaces() { return parkingSpaces; }
    public void setParkingSpaces(int parkingSpaces) { this.parkingSpaces = parkingSpaces; }
}
