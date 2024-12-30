package com.example.loborems.models.services;

import com.example.loborems.models.Interfaces.PropertyDAO;
import com.example.loborems.models.CommercialProperty;
import com.example.loborems.models.Property;
import com.example.loborems.models.PropertyFactory;
import com.example.loborems.models.ResidentialProperty;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.io.File;


public class PropertyService {

    private PropertyDAO propertyDAO;

    // Constructor to inject DAO
    public PropertyService() {
        this.propertyDAO = new PropertyDAOImpl();
    }

    public void saveProperty(String propertyType, String title, String location, double size,
                             double price, String features, String status,
                             Integer bedrooms, CheckBox hasGardenCheckBox,
                             Integer floors, Integer parking, List<File> photoFiles) {

        // Use the factory to create the property based on type
        Property property = PropertyFactory.createProperty(propertyType);
        property.setTitle(title);
        property.setLocation(location);
        property.setSize(size);
        property.setPrice(price);
        property.setFeatures(features);
        property.setStatus(status);
        // Handle type-specific properties
        if ("Residential".equals(propertyType)) {
            if (bedrooms != null) {
                ((ResidentialProperty) property).setNumberOfBedrooms(bedrooms);
            }
            if (hasGardenCheckBox != null) {
                ((ResidentialProperty) property).setHasGarden(hasGardenCheckBox.isSelected());
            }
        } else if ("Commercial".equals(propertyType)) {
            if (floors != null) {
                ((CommercialProperty) property).setNumberOfFloors(floors);
            }
            if (parking != null) {
                ((CommercialProperty) property).setParkingSpaces(parking);
            }
        }


//handle photos
        if (photoFiles != null && !photoFiles.isEmpty()) {
            StringBuilder imageDataBuilder = new StringBuilder();
            for (File photoFile : photoFiles) {
                try {
                    byte[] imageData = Files.readAllBytes(photoFile.toPath());
                    String base64Image = Base64.getEncoder().encodeToString(imageData);
                    imageDataBuilder.append(base64Image).append(",");
                } catch (IOException e) {
                    throw new RuntimeException("Failed to process image: " + photoFile.getName(), e);
                }
            }
            // Remove last comma
            if (imageDataBuilder.length() > 0) {
                imageDataBuilder.setLength(imageDataBuilder.length() - 1);
            }
            property.setImages(imageDataBuilder.toString());
        }

        propertyDAO.save(property);
    }
    // Get all properties method
    public List<Property> getAllProperties() {
        return propertyDAO.getAllProperties(); // Calls the DAO method to fetch all properties
    }
}