package com.example.loborems.controllers;

import com.example.loborems.models.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PropertyItemController {
    @FXML
    public Button viewMoreButton;
    @FXML
    private Label propertyName;  // Changed from titleLabel
    @FXML
    private Label locationLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label sizeLabel;

    public void setPropertyDetails(Property property) {

        if (property != null) {
            propertyName.setText(property.getTitle());
            locationLabel.setText("📍 " + property.getLocation());
            priceLabel.setText("💰 " + String.format("%,.2f", property.getPrice()));
            sizeLabel.setText("📐 " + property.getSize());

        }
    }

    @FXML
    public void onViewButtonClicked() {
        try {
            // Get the current stage (window) from the button that triggered the event
            Stage stage = (Stage) viewMoreButton.getScene().getWindow();

            // Load the new scene from FXML
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/loborems/PropertyDetails/property-details.fxml")));

            // Set the new scene
            stage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load Property Details Page.");
        }
    }

}
