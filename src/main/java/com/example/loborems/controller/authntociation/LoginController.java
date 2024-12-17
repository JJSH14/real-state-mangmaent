package com.example.loborems;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        // Add event handler for loginButton click
        loginButton.setOnAction(e -> handleLogin());
    }

    private void handleLogin() {
        // Fetch input values
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate input
        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill out all required fields.");
            return;
        }

        // Simulate a login process
        if (validateLogin(email, password)) {
            // Navigate to the Dashboard
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                Parent dashboardRoot = loader.load();

                // Get current scene and set the new root
                Scene currentScene = loginButton.getScene();
                currentScene.setRoot(dashboardRoot);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Unable to load the Dashboard.");
            }
        } else {
            showAlert("Login Failed", "Invalid credentials. Please try again.");
        }
    }

    private boolean validateLogin(String email, String password) {
        // Simulate validation logic
        return email.equals("user@example.com") && password.equals("password");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
