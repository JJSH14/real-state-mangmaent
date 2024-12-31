package com.example.loborems.controllers;

import com.example.loborems.models.User;
import com.example.loborems.models.services.UserDOAimp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.security.SecureRandom;
import java.util.Base64;

public class ForgotPasswordController {

    @FXML
    private TextField emailField;

    private final UserDOAimp userDAO = new UserDOAimp();

    /**
     * Generates a secure random token for resetting the password.
     */
    private String generateResetToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @FXML
    public void sendResetLink(ActionEvent event) {
        String email = emailField.getText().trim();

        try {
            // Fetch the user by email using UserDAO
            User user = userDAO.getByEmail(email);

            if (user != null) {
                String token = generateResetToken();

                // Save the token and expiration in the User entity
                user.setResetToken(token);
                user.setTokenExpiration(System.currentTimeMillis() + 15 * 60 * 1000); // Token valid for 15 minutes
                userDAO.update(user); // Update the user with the token and expiration

                // Generate the reset link
                String resetLink = "http://localhost:8080/reset-password?token=" + token;

                // Send the reset email
                EmailSender.sendResetEmail(email, resetLink);

                showAlert("Success", "A password reset link has been sent to your email.");
            } else {
                showAlert("Error", "Email not found in the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Email Error", "Failed to send the reset email.");
        }
    }

    /**
     * Displays an alert dialog with the specified title and message.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
