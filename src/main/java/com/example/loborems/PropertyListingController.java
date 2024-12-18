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
        // Load the FXML file for the new scene
        Parent root = FXMLLoader.load(getClass().getResource("add-property.fxml"));

        // Get the current stage from the event source
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Create a new scene with specified dimensions (900x650) and add the CSS stylesheet
        scene = new Scene(root, 900, 650); // Set dimensions here
        scene.getStylesheets().add(getClass().getResource("addPropertyStyles.css").toExternalForm());

        // Set the new scene and show the stage
        stage.setScene(scene);
        stage.show();
    }


}
