package com.example.loborems.models.services;

import com.example.loborems.models.Interfaces.PropertyDAO;
import com.example.loborems.models.CommercialProperty;
import com.example.loborems.models.Property;
import com.example.loborems.models.PropertyFactory;

public class PropertyService {

    private PropertyDAO propertyDAO;

    // Constructor to inject DAO
    public PropertyService() {
        this.propertyDAO = new PropertyDAOImpl(); // Dependency Injection (DI) could also be used here
    }

    public void saveProperty(String type, String title, String location, double size, double price, String features, String status) {
        // Use the factory to create the property
        Property property = PropertyFactory.createProperty(type);
        property.setTitle(title);
        property.setLocation(location);
        property.setSize(size);
        property.setPrice(price);
        property.setFeatures(features);
        property.setStatus(status);

        if (property instanceof CommercialProperty) {
            ((CommercialProperty) property).setNumberOfFloors(5);  // Set specific properties for commercial
            ((CommercialProperty) property).setParkingSpaces(20);
        }

        // Save property object using DAO
        propertyDAO.save(property);
    }
}
