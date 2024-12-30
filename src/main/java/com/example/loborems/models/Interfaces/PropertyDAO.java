package com.example.loborems.models.Interfaces;

import com.example.loborems.models.Property;
import java.util.List;

public interface PropertyDAO {
    void save(Property property);
    Property getById(int id);
    void update(Property property);
    void delete(Property property);
    List<Property> getAllProperties();  // Add this method to the interface
}
