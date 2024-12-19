package com.example.loborems.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent; // Correct import

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PropertyListingController implements javafx.fxml.Initializable {
    @FXML
    public Button navigateButton;
    public Button backButton;
    public Button detailsPage;    @FXML
    private Parent root;
    @FXML
    private VBox propertyListContainer;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialization logic (if needed)
    }


    @FXML
    public void handleButtonClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/loborems/AddProperty/add-property.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 900, 650);
        stage.setScene(scene);
        stage.show();
    }


    public void handleBackClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onViewButtonClicked() {
        try {
            Stage stage = (Stage) detailsPage.getScene().getWindow();
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/loborems/PropertyDetails/property-details.fxml")));
            stage.setScene(newScene);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load Client List Page.");
        }
    }
}
