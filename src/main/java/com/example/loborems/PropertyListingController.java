package com.example.loborems;

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
        // Initialization logic (if needed)
    }


    @FXML
    public void handleButtonClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("add-property.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 900, 650);
        scene.getStylesheets().add(getClass().getResource("addPropertyStyles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    public void handleBackClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
