package com.example.loborems.modules;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    private StringProperty id;
    private StringProperty name;
    private StringProperty email;
    private StringProperty phone;
    private StringProperty property;
    private StringProperty role;

    public Client(String id, String name, String email, String phone, String property, String role) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.property = new SimpleStringProperty(property);
        this.role = new SimpleStringProperty(role);
    }

    // Getter and setter for ID
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return id;
    }

    // Getter and setter for Name
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    // Getter and setter for Email
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    // Getter and setter for Phone
    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    // Getter and setter for Property
    public String getProperty() {
        return property.get();
    }

    public void setProperty(String property) {
        this.property.set(property);
    }

    public StringProperty propertyProperty() {
        return property;
    }

    // Getter and setter for Role
    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public StringProperty roleProperty() {
        return role;
    }
}



