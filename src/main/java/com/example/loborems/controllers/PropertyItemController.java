package com.example.loborems.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PropertyItemController {

    @FXML
    private Button viewMoreButton;  // Correctly use viewMoreButton

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
