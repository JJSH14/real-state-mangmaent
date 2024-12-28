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
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PropertyListingController implements javafx.fxml.Initializable {
    @FXML
    public Button navigateButton;
    public Button backButton;
    public Button detailsPage;
    @FXML
    private Parent root;
    @FXML
    private VBox propertyListContainer;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handleButtonClick(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/loborems/AddProperty/add-property.fxml"));
            Parent root = loader.load();

            AddPropertyController controller = loader.getController();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root,900,750);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void handleBackClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}