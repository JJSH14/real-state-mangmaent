package com.example.loborems.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Residential")
public class ResidentialProperty extends Property {
    private int numberOfBedrooms;
    private boolean hasGarden;
    @Override
    public void setType(String type) {
        // Set the property type explicitly if needed
        // In this case, it's already set by the DiscriminatorValue annotation
    }
    public int getNumberOfBedrooms() { return numberOfBedrooms; }
    public void setNumberOfBedrooms(int numberOfBedrooms) { this.numberOfBedrooms = numberOfBedrooms; }
    public boolean isHasGarden() { return hasGarden; }
    public void setHasGarden(boolean hasGarden) { this.hasGarden = hasGarden; }
}
