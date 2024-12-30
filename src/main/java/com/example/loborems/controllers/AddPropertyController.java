package com.example.loborems.controllers;

import com.example.loborems.models.*;
import com.example.loborems.models.services.PropertyService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

public class AddPropertyController {
    private PropertyService propertyService = new PropertyService();
    @FXML public TextField priceField;
    @FXML public TextArea featuresArea;
    @FXML public ComboBox<String> statusComboBox;
    @FXML public Label photoCountLabel;
    @FXML public Button uploadPhotosButton;
    @FXML public ComboBox<String> propertyTypeComboBox;
    @FXML public TextField locationField;
    @FXML public TextField titleField;
    @FXML public TextField sizeField;

    // New fields for property types
    @FXML public VBox residentialFields;
    @FXML public VBox commercialFields;
    @FXML public TextField bedroomsField;
    @FXML public CheckBox hasGardenCheckBox;
    @FXML public TextField floorsField;
    @FXML public TextField parkingField;


    // Remove @FXML from these as they're not in the FXML

    private List<File> selectedPhotos = new ArrayList<>();
    private Property currentProperty;


    @FXML
    public void initialize() {
        propertyTypeComboBox.setItems(FXCollections.observableArrayList("Residential", "Commercial"));
    }
    @FXML
    public void handlePropertyTypeChange(ActionEvent event) {
        String selectedType = propertyTypeComboBox.getValue();

        if (selectedType == null || selectedType.trim().isEmpty()) {
            System.out.println("Please select a property type.");
            return;
        }

        boolean isResidential = "Residential".equals(selectedType);
        boolean isCommercial = "Commercial".equals(selectedType);

        residentialFields.setVisible(isResidential);
        residentialFields.setManaged(isResidential);
        commercialFields.setVisible(isCommercial);
        commercialFields.setManaged(isCommercial);

        System.out.println("Selected Property Type: " + selectedType);
    }



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

            try {
                String propertyType = propertyTypeComboBox.getValue();
                String title = titleField.getText();
                String location = locationField.getText();
                double size = Double.parseDouble(sizeField.getText());
                double price = Double.parseDouble(priceField.getText());
                String features = featuresArea.getText();
                String status = statusComboBox.getValue();

                if ("Residential".equals(propertyType)) {
                    int bedrooms = Integer.parseInt(bedroomsField.getText());
                    propertyService.saveProperty(
                            propertyType, title, location, size, price, features, status,
                            bedrooms, hasGardenCheckBox, null, null, selectedPhotos
                    );
                } else if ("Commercial".equals(propertyType)) {
                    int floors = Integer.parseInt(floorsField.getText());
                    int parking = Integer.parseInt(parkingField.getText());
                    propertyService.saveProperty(
                            propertyType, title, location, size, price, features, status,
                            null, null, floors, parking, selectedPhotos
                    );
                }

                showAlert("Property Saved", "Property has been successfully added.");
                navigateToPropertyListing(actionEvent);

            } catch (NumberFormatException e) {
                showAlert("Validation Error", "Please check numeric fields for valid numbers.");
            }
        }
    }


    private void updatePropertyFromFields() {
        if (currentProperty == null) {
            currentProperty = PropertyFactory.createProperty(propertyTypeComboBox.getValue());
        }

        currentProperty.setTitle(titleField.getText());
        currentProperty.setLocation(locationField.getText());
        currentProperty.setSize(Double.parseDouble(sizeField.getText()));
        currentProperty.setPrice(Double.parseDouble(priceField.getText()));
        currentProperty.setFeatures(featuresArea.getText());
        currentProperty.setStatus(statusComboBox.getValue());

        if (currentProperty instanceof ResidentialProperty residential) {
            residential.setNumberOfBedrooms(Integer.parseInt(bedroomsField.getText()));
            residential.setHasGarden(hasGardenCheckBox.isSelected());
        } else if (currentProperty instanceof CommercialProperty commercial) {
            commercial.setNumberOfFloors(Integer.parseInt(floorsField.getText()));
            commercial.setParkingSpaces(Integer.parseInt(parkingField.getText()));
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
                selectedPhotos.isEmpty() &&
                allFieldsEmpty().test(bedroomsField) &&
                !hasGardenCheckBox.isSelected() &&
                allFieldsEmpty().test(floorsField) &&
                allFieldsEmpty().test(parkingField);
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
        validateTypeSpecificFields(errors);

        if (errors.length() > 0) {
            showAlert("Validation Error", errors.toString());
            return false;
        }
        return true;
    }
    private void validateTypeSpecificFields(StringBuilder errors) {
        String propertyType = propertyTypeComboBox.getValue();
        if ("Residential".equals(propertyType)) {
            validateField(bedroomsField, "Number of bedrooms is required.", errors);
            try {
                if (!bedroomsField.getText().trim().isEmpty()) {
                    int bedrooms = Integer.parseInt(bedroomsField.getText().trim());
                    if (bedrooms <= 0) {
                        errors.append("Number of bedrooms must be greater than 0.\n");
                    }
                }
            } catch (NumberFormatException e) {
                errors.append("Invalid number of bedrooms format.\n");
            }
        } else if ("Commercial".equals(propertyType)) {
            validateField(floorsField, "Number of floors is required.", errors);
            validateField(parkingField, "Number of parking spaces is required.", errors);

            try {
                if (!floorsField.getText().trim().isEmpty()) {
                    int floors = Integer.parseInt(floorsField.getText().trim());
                    if (floors <= 0) {
                        errors.append("Number of floors must be greater than 0.\n");
                    }
                }
            } catch (NumberFormatException e) {
                errors.append("Invalid number of floors format.\n");
            }

            try {
                if (!parkingField.getText().trim().isEmpty()) {
                    int parkingSpaces = Integer.parseInt(parkingField.getText().trim());
                    if (parkingSpaces < 0) {
                        errors.append("Number of parking spaces cannot be negative.\n");
                    }
                }
            } catch (NumberFormatException e) {
                errors.append("Invalid number of parking spaces format.\n");
            }
        }
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
            String priceText = priceField.getText().trim();
            if (priceText.isEmpty()) {
                errors.append("Price is required.\n");
            } else {
                double price = Double.parseDouble(priceText);
                if (price <= 0) {
                    errors.append("Price must be greater than 0.\n");
                }
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
                    errors.append("Property size must be greater than 0.\n");
                }
            }
        } catch (NumberFormatException e) {
            errors.append("Invalid size format.\n");
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
        bedroomsField.clear();
        hasGardenCheckBox.setSelected(false);
        floorsField.clear();
        parkingField.clear();
        selectedPhotos.clear();
        photoCountLabel.setText("No photos selected");

        // Hide both type-specific field containers
        residentialFields.setVisible(false);
        residentialFields.setManaged(false);
        commercialFields.setVisible(false);
        commercialFields.setManaged(false);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

    private boolean hasUnsavedChanges() {
        return anyFieldModified().test(titleField) ||
                anyFieldModified().test(locationField) ||
                anyFieldModified().test(priceField) ||
                anyFieldModified().test(featuresArea) ||
                anyFieldModified().test(sizeField) ||
                propertyTypeComboBox.getValue() != null ||
                statusComboBox.getValue() != null ||
                !selectedPhotos.isEmpty() ||
                anyFieldModified().test(bedroomsField) ||
                hasGardenCheckBox.isSelected() ||
                anyFieldModified().test(floorsField) ||
                anyFieldModified().test(parkingField);
    }

    private Predicate<javafx.scene.Node> anyFieldModified() {
        return field -> field instanceof TextField && !((TextField) field).getText().trim().isEmpty() ||
                field instanceof TextArea && !((TextArea) field).getText().trim().isEmpty() ||
                field instanceof ComboBox && ((ComboBox<?>) field).getValue() != null;
    }

    private boolean confirmDiscardChanges() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unsaved Changes");
        alert.setHeaderText(null);
        alert.setContentText("You have unsaved changes. Do you want to discard them?");
        return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }
}