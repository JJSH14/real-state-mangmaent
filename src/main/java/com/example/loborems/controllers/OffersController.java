package com.example.loborems.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;


public class OffersController {

    @FXML
    private Button backButton; // Define the downloadButton

      @FXML
    public void handleBack(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }
}
