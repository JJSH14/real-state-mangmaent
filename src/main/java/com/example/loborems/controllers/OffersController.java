package com.example.loborems.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OffersController {

    @FXML
    private Button backButtonOffers;
    @FXML
    private Button backButtonClients;

    @FXML
    private TextField clientNameField;
    @FXML
    private ComboBox<String> propertyTypeCombo;
    @FXML
    private ComboBox<String> offerTypeCombo;
    @FXML
    private TextField priceField;
    @FXML
    private TableView<String[]> offersTable;
    @FXML
    private TableColumn<String[], String> clientNameColumn;
    @FXML
    private TableColumn<String[], String> propertyTypeColumn;
    @FXML
    private TableColumn<String[], String> offerTypeColumn;
    @FXML
    private TableColumn<String[], String> priceColumn;
    @FXML
    private TableColumn<String[], String> statusColumn;

    @FXML
    private TextField clientNameInputField;
    @FXML
    private TextField contactInfoField;
    @FXML
    private ComboBox<String> clientCategoryCombo;
    @FXML
    private TextField preferencesField;
    @FXML
    private TableView<String[]> clientsTable;
    @FXML
    private TableColumn<String[], String> clientNameColumn2;
    @FXML
    private TableColumn<String[], String> contactInfoColumn;
    @FXML
    private TableColumn<String[], String> categoryColumn;
    @FXML
    private TableColumn<String[], String> preferencesColumn;

    private ObservableList<String[]> offerData = FXCollections.observableArrayList();
    private ObservableList<String[]> clientData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        propertyTypeCombo.setItems(FXCollections.observableArrayList("House", "Apartment", "Condo"));
        offerTypeCombo.setItems(FXCollections.observableArrayList("Sale", "Rent"));
        clientCategoryCombo.setItems(FXCollections.observableArrayList("Regular", "VIP", "New"));

        clientNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));
        propertyTypeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));
        offerTypeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[3]));
        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[4]));
        offersTable.setItems(offerData);

        clientNameColumn2.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));
        contactInfoColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));
        categoryColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));
        preferencesColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[3]));
        clientsTable.setItems(clientData);
    }

    @FXML
    public void handleAddOffer(ActionEvent event) {
        String clientName = clientNameField.getText();
        String propertyType = propertyTypeCombo.getSelectionModel().getSelectedItem();
        String offerType = offerTypeCombo.getSelectionModel().getSelectedItem();
        String price = priceField.getText();

        String[] newOffer = {clientName, propertyType, offerType, price, "Pending"};
        offerData.add(newOffer);

        clientNameField.clear();
        propertyTypeCombo.getSelectionModel().clearSelection();
        offerTypeCombo.getSelectionModel().clearSelection();
        priceField.clear();
    }

    @FXML
    public void handleAddClient(ActionEvent event) {
        String name = clientNameInputField.getText();
        String contactInfo = contactInfoField.getText();
        String category = clientCategoryCombo.getSelectionModel().getSelectedItem();
        String preferences = preferencesField.getText();

        String[] newClient = {name, contactInfo, category, preferences};
        clientData.add(newClient);

        clientNameInputField.clear();
        contactInfoField.clear();
        clientCategoryCombo.getSelectionModel().clearSelection();
        preferencesField.clear();
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }
}
