package com.example.loborems.controllers;

import com.example.loborems.models.User;
import com.example.loborems.models.services.UserDOAimp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

public class LoginController {
    private final UserDOAimp userDOAimp = new UserDOAimp();

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
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Validate input
        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill out all required fields.");
            return;
        }

        try {
            if (validateLogin(email, password)) {
                // Navigate to the Dashboard
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml"));
                    Parent dashboardRoot = loader.load();

                    // Get current scene and set the new root
                    Scene currentScene = loginButton.getScene();
                    currentScene.setRoot(dashboardRoot);
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Error", "Unable to load the Dashboard.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid email or password. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during login: " + e.getMessage());
        }
    }

    private boolean validateLogin(String email, String password) {
        try {
            // Get user from database by email
            User user = userDOAimp.getByEmail(email);

            // Check if user exists
            if (user == null) {
                return false;
            }

            // Verify password using BCrypt
            return BCrypt.checkpw(password, user.getPassword());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}