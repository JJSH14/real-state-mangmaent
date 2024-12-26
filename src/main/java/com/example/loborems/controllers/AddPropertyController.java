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
import java.util.function.Predicate;

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
            if (selectedPhotos.size() != 5) {
                showAlert("Validation Error", "Please upload 5 photos.");
                return;
            }

            showAlert("Property Saved", "Property has been successfully added.");
            navigateToPropertyListing(actionEvent);
            clearForm();
        }
    }

    private void navigateToPropertyListing(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/loborems/PropertyListing/property-listing.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showAlert("Navigation Error", "Could not load the previous page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        if (areAllFieldsEmpty()) {
            navigateToPropertyListing(actionEvent);
        } else {
            clearForm();
        }
    }

    private boolean areAllFieldsEmpty() {
        return allFieldsEmpty().test(titleField) &&
                allFieldsEmpty().test(locationField) &&
                allFieldsEmpty().test(priceField) &&
                allFieldsEmpty().test(featuresArea) &&
                allFieldsEmpty().test(sizeField) &&
                propertyTypeComboBox.getValue() == null &&
                statusComboBox.getValue() == null &&
                selectedPhotos.isEmpty();
    }

    private Predicate<javafx.scene.Node> allFieldsEmpty() {
        return field -> field instanceof TextField && ((TextField) field).getText().trim().isEmpty() ||
                field instanceof TextArea && ((TextArea) field).getText().trim().isEmpty() ||
                field instanceof ComboBox && ((ComboBox<?>) field).getValue() == null;
    }

    private boolean validateInput() {
        StringBuilder errors = new StringBuilder();

        validateField(titleField, "Property title is required.", errors);
        validateField(locationField, "Location is required.", errors);
        validateComboBox(propertyTypeComboBox, "Property type must be selected.", errors);
        validateComboBox(statusComboBox, "Property status must be selected.", errors);
        validatePrice(errors);
        validateSize(errors);

        if (errors.length() > 0) {
            showAlert("Validation Error", errors.toString());
            return false;
        }
        return true;
    }

    private void validateField(TextField field, String errorMessage, StringBuilder errors) {
        if (field.getText().trim().isEmpty()) {
            errors.append(errorMessage).append("\n");
        }
    }

    private void validateComboBox(ComboBox<String> comboBox, String errorMessage, StringBuilder errors) {
        if (comboBox.getValue() == null) {
            errors.append(errorMessage).append("\n");
        }
    }

    private void validatePrice(StringBuilder errors) {
        try {
            double price = Double.parseDouble(priceField.getText());
            if (price <= 0) {
                errors.append("Price must be a positive number.\n");
            }
        } catch (NumberFormatException e) {
            errors.append("Invalid price format.\n");
        }
    }

    private void validateSize(StringBuilder errors) {
        try {
            String sizeText = sizeField.getText().trim();

            if (sizeText.isEmpty()) {
                errors.append("Property size is required.\n");
            } else {
                double size = Double.parseDouble(sizeText);

                if (size <= 0) {
                    errors.append("Property size must be a positive number.\n");
                }
            }
        } catch (NumberFormatException e) {
            errors.append("Invalid size format. Please enter a valid number.\n");
        }
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

    private boolean hasUnsavedChanges() {
        return anyFieldModified().test(titleField) ||
                anyFieldModified().test(locationField) ||
                anyFieldModified().test(priceField) ||
                anyFieldModified().test(featuresArea) ||
                anyFieldModified().test(sizeField) ||
                propertyTypeComboBox.getValue() != null ||
                statusComboBox.getValue() != null ||
                !selectedPhotos.isEmpty();
    }

    private Predicate<javafx.scene.Node> anyFieldModified() {
        return field -> field instanceof TextField && !((TextField) field).getText().trim().isEmpty() ||
                field instanceof TextArea && !((TextArea) field).getText().trim().isEmpty() ||
                field instanceof ComboBox && ((ComboBox<?>) field).getValue() != null;
    }

    public void handleBack(ActionEvent actionEvent) {
        if (hasUnsavedChanges()) {
            if (confirmDiscardChanges()) {
                navigateToPropertyListing(actionEvent);
            }
        } else {
            navigateToPropertyListing(actionEvent);
        }
    }

    private boolean confirmDiscardChanges() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unsaved Changes");
        alert.setHeaderText(null);
        alert.setContentText("You have unsaved changes. Do you want to discard them?");
        return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }
}
