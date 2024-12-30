package com.example.loborems.controllers;

import java.io.IOException;
import java.util.List;

import com.example.loborems.models.Offer;
import com.example.loborems.models.Offer.OfferType;
import com.example.loborems.models.Offer.PropertyType;
import com.example.loborems.models.Offer.Status;
import com.example.loborems.models.services.OfferDAO;
import com.example.loborems.models.services.OfferDAOImpl;

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

public class OffersController {

    @FXML
    private Button backButtonOffers;

    @FXML
    private TextField clientNameField;
    @FXML
    private ComboBox<PropertyType> propertyTypeCombo;
    @FXML
    private ComboBox<OfferType> offerTypeCombo;
    @FXML
    private TextField priceField;
    @FXML
    private TableView<Offer> offersTable;
    @FXML
    private TableColumn<Offer, String> clientNameColumn;
    @FXML
    private TableColumn<Offer, PropertyType> propertyTypeColumn;
    @FXML
    private TableColumn<Offer, OfferType> offerTypeColumn;
    @FXML
    private TableColumn<Offer, Double> priceColumn;
    @FXML
    private TableColumn<Offer, Status> statusColumn;

    private ObservableList<Offer> offerData = FXCollections.observableArrayList();
    private OfferDAO offerDAO = new OfferDAOImpl();

    @FXML
    public void initialize() {
        // Initialize ComboBoxes with enum values
        propertyTypeCombo.setItems(FXCollections.observableArrayList(PropertyType.values()));
        offerTypeCombo.setItems(FXCollections.observableArrayList(OfferType.values()));

        // Set up TableColumns for the offersTable
        clientNameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getClientName()));
        propertyTypeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPropertyType()));
        offerTypeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getOfferType()));
        priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()).asObject());
        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getStatus()));
        offersTable.setItems(offerData);

        // Load existing offers
        // loadOffers();
    }

    private void loadOffers() {
        List<Offer> offers = offerDAO.findAll();
        offerData.setAll(offers);
    }

    @FXML
    public void handleAddOffer(ActionEvent event) {
        String clientName = clientNameField.getText();
        PropertyType propertyType = propertyTypeCombo.getSelectionModel().getSelectedItem();
        OfferType offerType = offerTypeCombo.getSelectionModel().getSelectedItem();
        double price = Double.parseDouble(priceField.getText());

        Offer newOffer = new Offer(clientName, propertyType, offerType, price, Status.PENDING);
        offerDAO.save(newOffer);
        offerData.add(newOffer);

        // Clear the input fields
        clientNameField.clear();
        propertyTypeCombo.getSelectionModel().clearSelection();
        offerTypeCombo.getSelectionModel().clearSelection();
        priceField.clear();
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }
}