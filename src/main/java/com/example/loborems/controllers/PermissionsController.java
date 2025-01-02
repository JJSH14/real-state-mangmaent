package com.example.loborems.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PermissionsController {

    @FXML
    private TableView<?> usersTable;

    @FXML
    private TableColumn<?, ?> userIdColumn;

    @FXML
    private TableColumn<?, ?> userNameColumn;

    @FXML
    private TableColumn<?, ?> roleColumn;

    @FXML
    private CheckBox viewPermission;

    @FXML
    private CheckBox editPermission;

    @FXML
    private CheckBox deletePermission;

    @FXML
    private Button savePermissionsButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void handleSavePermissions() {
        boolean view = viewPermission.isSelected();
        boolean edit = editPermission.isSelected();
        boolean delete = deletePermission.isSelected();

        // Sample logic: Display selected permissions
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Permissions Saved");
        alert.setHeaderText("Selected Permissions:");
        alert.setContentText(
                "View: " + view + "\n" +
                        "Edit: " + edit + "\n" +
                        "Delete: " + delete
        );
        alert.showAndWait();
    }

    @FXML
    public void handleCancel() {
        // Clear all checkboxes
        viewPermission.setSelected(false);
        editPermission.setSelected(false);
        deletePermission.setSelected(false);

        // Show cancellation message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Action Cancelled");
        alert.setHeaderText(null);
        alert.setContentText("Changes have been discarded.");
        alert.showAndWait();
    }
}
