package com.example.loborems.Controller;

import com.example.loborems.modules.Client;
import com.example.loborems.modules.DOAClient;
import com.example.loborems.modules.DOAInteraction;
import com.example.loborems.modules.Interaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientInteractionController {

    private static final Logger LOGGER = Logger.getLogger(ClientInteractionController.class.getName());

    @FXML
    private ComboBox<Client> clientSelect; // ComboBox for client selection

    @FXML
    private ComboBox<String> interactionType; // ComboBox for interaction type

    @FXML
    private DatePicker datePicker; // DatePicker for selecting date

    @FXML
    private TextArea interactionDetails; // TextArea for interaction details

    @FXML
    private Button cancelButton; // Cancel button

    @FXML
    private Button saveButton; // Save button

    @FXML
    private TableView<Interaction> tableView; // TableView for displaying interactions


    public ClientInteractionController() {
    }

    @FXML
    void initialize() {
        LOGGER.info("Initializing ClientInteraction class.");

        // Adding interaction types to ComboBox
        interactionType.getItems().addAll("Call", "Email", "Meeting");
        LOGGER.info("Interaction types added to ComboBox: Call, Email, Meeting.");



        saveButton.setOnAction(event -> {
            LOGGER.info("Save button clicked.");

        });

        cancelButton.setOnAction(event -> {
            LOGGER.info("Cancel button clicked.");

        });
    }



}
