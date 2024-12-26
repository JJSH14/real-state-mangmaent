package com.example.loborems.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

import com.example.loborems.models.Client;

public class ClientListController {

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> idColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;

    @FXML
    private TableColumn<Client, String> phoneColumn;

    @FXML
    private TableColumn<Client, String> propertyColumn;

    @FXML
    private TableColumn<Client, String> roleColumn;

    @FXML
    private TableColumn<Client, Void> editColumn;

    @FXML
    private TableColumn<Client, Void> removeColumn;

    @FXML
    private TextField nameField, emailField, phoneField, propertyField, roleField, searchField;

    @FXML
    private Button addClientButton;

    @FXML
    private Button backButton;

    private final ObservableList<Client> clientList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        bindTableColumns();
        addEditButtonToTable();
        addDeleteButtonToTable();

        clientTable.setItems(clientList);

        // Add search functionality
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchClients(newValue));
    }

    // Bind table columns to the Client properties
    private void bindTableColumns() {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        propertyColumn.setCellValueFactory(cellData -> cellData.getValue().propertyProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
    }

    // Handle Add/Update Client button click
    @FXML
    private void onAddClientButtonClicked() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String property = propertyField.getText().trim();
        String role = roleField.getText().trim();

        if (validateFields(name, email, phone, role)) {
            String newId = String.valueOf(clientList.size() + 1); // Generate unique ID
            clientList.add(new Client( name, email, phone, property, role));
            clearFields();
        }
    }

    // Add Edit button to each row
    private void addEditButtonToTable() {
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setStyle("-fx-background-color: #14274d; -fx-text-fill: white;");
                editButton.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    populateFieldsForEdit(client);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : editButton);
            }
        };

        editColumn.setCellFactory(cellFactory);
    }

    // Add Delete button to each row
    private void addDeleteButtonToTable() {
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #14274d; -fx-text-fill: white;");
                deleteButton.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    clientList.remove(client);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        };

        removeColumn.setCellFactory(cellFactory);
    }

    // Populate fields for editing an existing client
    private void populateFieldsForEdit(Client client) {
        nameField.setText(client.getName());
        emailField.setText(client.getEmail());
        phoneField.setText(client.getPhone());
        propertyField.setText(client.getProperty());
        roleField.setText(client.getRole());

        addClientButton.setText("Update Client");
        addClientButton.setOnAction(event -> updateClient(client));
    }

    // Update client details
    private void updateClient(Client client) {
        if (validateFields(nameField.getText(), emailField.getText(), phoneField.getText(), roleField.getText())) {
            client.setName(nameField.getText());
            client.setEmail(emailField.getText());
            client.setPhone(phoneField.getText());
            client.setProperty(propertyField.getText());
            client.setRole(roleField.getText());

            clientTable.refresh();
            resetAddClientButton();
        }
    }

    // Reset Add Client button to default state
    private void resetAddClientButton() {
        clearFields();
        addClientButton.setText("Add Client");
        addClientButton.setOnAction(event -> onAddClientButtonClicked());
    }

    // Clear input fields
    private void clearFields() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        propertyField.clear();
        roleField.clear();
    }

    // Validate fields for non-empty and email format
    private boolean validateFields(String name, String email, String phone, String role) {
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || role.isEmpty()) {
            showAlert("Validation Error", "All fields are required.", Alert.AlertType.ERROR);
            return false;
        }
        if (!email.contains("@")) {
            showAlert("Validation Error", "Invalid email format.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    // Show alert message
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Filter clients based on search query
    private void searchClients(String searchQuery) {
        ObservableList<Client> filteredList = FXCollections.observableArrayList();
        for (Client client : clientList) {
            if (client.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredList.add(client);
            }
        }
        clientTable.setItems(searchQuery.isEmpty() ? clientList : filteredList);
    }

    // Navigate back to the dashboard
    @FXML
    private void onBackButtonClicked() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml")));
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Failed to load Dashboard.", Alert.AlertType.ERROR);
        }
    }
}
