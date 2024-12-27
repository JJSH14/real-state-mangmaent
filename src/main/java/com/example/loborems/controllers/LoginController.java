package com.example.loborems.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    }

    public void handleLogin(ActionEvent event) {
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
                Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml"));
                Scene newScene = new Scene(secondRoot);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(newScene);
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
        return email.equals("salah") && password.equals("11");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void goToForget(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/ForgotPassword/forgot-password.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }

}
