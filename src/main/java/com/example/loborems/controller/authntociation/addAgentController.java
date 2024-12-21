package com.example.loborems.controller.authntociation;

import com.example.loborems.models.Role;
import com.example.loborems.models.User;
import com.example.loborems.models.services.RoleDOAimp;
import com.example.loborems.models.services.UserDOAimp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import org.mindrot.jbcrypt.BCrypt;

public class addAgentController {
    private final UserDOAimp userDOAimp = new UserDOAimp();
    private final RoleDOAimp roleDOAimp = new RoleDOAimp();

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField full_name;

    @FXML
    private JFXTextField passwordField;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXCheckBox acceptTerms;

    @FXML
    private JFXComboBox<String> roleChoiceBox;

    @FXML
    public void initialize() {
        // Populate ComboBox with roles
        roleChoiceBox.setItems(FXCollections.observableArrayList("admin", "agent"));

        // Add listeners for validation
        full_name.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        emailField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        acceptTerms.selectedProperty().addListener((observable, oldValue, newValue) -> validateFields());
        roleChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> validateFields());
    }

    @FXML
    public void event(ActionEvent event) {
        if (!validateFields()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill all required fields, select a role, and accept the terms.");
            return;
        }

        try {
            // Check if email already exists
            if (userDOAimp.getByEmail(emailField.getText().trim()) != null) {
                showAlert(Alert.AlertType.ERROR, "Registration Error", "Email already exists");
                return;
            }

            // Create new User object
            User newUser = new User();
            newUser.setFullName(full_name.getText());
            newUser.setEmail(emailField.getText().trim());

            // Hash the password before saving
            String hashedPassword = hashPassword(passwordField.getText().trim());
            newUser.setPassword(hashedPassword);

            // Save the role selected from the ComboBox
            String selectedRole = roleChoiceBox.getValue();
            Role role = roleDOAimp.findByName(selectedRole);

            if (role == null) {
                showAlert(Alert.AlertType.ERROR, "Role Error", "Selected role does not exist");
                return;
            }

            newUser.setRole(role);

            // Save the user using UserDOAimp
            userDOAimp.save(newUser);

            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "Agent account created successfully!");

            // Clear the fields
            clearFields();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Registration Error", "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
    }

    private boolean validateFields() {
        addButton.setDisable(true);

        // Check if any field is empty
        if (isFieldEmpty(emailField) ||
                isFieldEmpty(passwordField) ||
                isFieldEmpty(full_name)) {
            return false;
        }

        // Validate email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!emailField.getText().trim().matches(emailRegex)) {
            return false;
        }

        // Validate password length and complexity
        String password = passwordField.getText().trim();
        if (!isPasswordValid(password)) {
            return false;
        }

        // Check if a role is selected
        if (roleChoiceBox.getValue() == null || roleChoiceBox.getValue().trim().isEmpty()) {
            return false;
        }

        // Check if terms are accepted
        if (!acceptTerms.isSelected()) {
            return false;
        }

        addButton.setDisable(false);
        return true;
    }

    private boolean isPasswordValid(String password) {
        // Minimum length of 8 characters
        if (password.length() < 8) return false;

        // Check for at least one lowercase letter
        if (!password.matches(".*[a-z].*")) return false;

        // Check for at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) return false;

        // Check for at least one number
        if (!password.matches(".*[0-9].*")) return false;

        // Check for at least one special character
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) return false;

        return true;
    }

    private boolean isFieldEmpty(JFXTextField field) {
        return field.getText() == null || field.getText().trim().isEmpty();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        emailField.clear();
        passwordField.clear();
        full_name.clear();
        acceptTerms.setSelected(false);
        roleChoiceBox.getSelectionModel().clearSelection();
    }
}