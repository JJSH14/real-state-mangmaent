package com.example.loborems.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class AddPropertyController {
    @FXML
    public TextField priceField;
    @FXML
    public TextArea featuresArea;
    @FXML
    public ComboBox<String> statusComboBox;
    @FXML
    public Label photoCountLabel;
    @FXML
    public Button uploadPhotosButton;
    @FXML
    public ComboBox<String> propertyTypeComboBox;
    @FXML
    public TextField locationField;
    @FXML
    public TextField titleField;
    @FXML
    public TextField sizeField;

    private List<File> selectedPhotos = new ArrayList<>();

    @FXML
    public void handlePhotoUpload(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Property Photos");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        List<File> files = fileChooser.showOpenMultipleDialog(uploadPhotosButton.getScene().getWindow());

        if (files != null) {
            selectedPhotos.addAll(files);
            photoCountLabel.setText(selectedPhotos.size() + " photo(s) selected");
        }
    }

    @FXML
    public void handleSaveProperty(ActionEvent actionEvent) {
        if (validateInput()) {
            // Here you would implement property saving logic
            // This could involve creating a Property object and saving to MongoDB
            showAlert("Property Saved", "Property has been successfully added.");
            clearForm();
        }
    }

    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        clearForm();
        // Optionally navigate back to previous scene
    }

    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();

        if (titleField.getText().trim().isEmpty()) {
            errors.append("Property title is required.\n");
        }
        if (locationField.getText().trim().isEmpty()) {
            errors.append("Location is required.\n");
        }
        if (propertyTypeComboBox.getValue() == null) {
            errors.append("Property type must be selected.\n");
        }
        if (statusComboBox.getValue() == null) {
            errors.append("Property status must be selected.\n");
        }
        try {
            double price = Double.parseDouble(priceField.getText());
            if (price <= 0) {
                errors.append("Price must be a positive number.\n");
            }
        } catch (NumberFormatException e) {
            errors.append("Invalid price format.\n");
        }
        try {
            String sizeText = sizeField.getText().trim();

            // Check if the size field is empty
            if (sizeText.isEmpty()) {
                errors.append("Property is required\n");
            } else {
                // Parse the size value only if it's not empty
                double size = Double.parseDouble(sizeText);

                if (size <= 0) {
                    errors.append("Property size must be a positive number.\n");
                }
            }
        } catch (NumberFormatException e) {
            errors.append("Invalid size format. Please enter a valid number.\n");
        }

        if (errors.length() > 0) {
            showAlert("Validation Error", errors.toString());
            return false;
        }
        return true;
    }

    private void clearForm() {
        titleField.clear();
        locationField.clear();
        priceField.clear();
        featuresArea.clear();
        sizeField.clear();
        propertyTypeComboBox.setValue(null);
        statusComboBox.setValue(null);
        selectedPhotos.clear();
        photoCountLabel.setText("No photos selected");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void handleBack(ActionEvent actionEvent) {
        try {
            // Load the previous scene (e.g., property list or dashboard)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/loborems/PropertyListing/property-listing.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            // Show an error alert if loading fails
            showAlert("Navigation Error", "Could not load the previous page: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

