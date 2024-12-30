package com.example.loborems.controllers;

import com.example.loborems.models.CommercialProperty;
import com.example.loborems.models.Property;
import com.example.loborems.models.ResidentialProperty;
import com.example.loborems.models.services.PropertyService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PropertyListingController implements javafx.fxml.Initializable {
    private final PropertyService propertyService = new PropertyService();

    @FXML
    public Button backButton;

    @FXML
    private FlowPane propertyListContainer;
    @FXML
    private Button addPropertyButton;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;




    private List<Property> properties;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing property listing...");

        // Fetch all properties
        properties = propertyService.getAllProperties();
        if (properties != null && !properties.isEmpty()) {
            System.out.println("Found " + properties.size() + " properties.");

            // Add each property card
            for (Property property : properties) {
                addPropertyItem(property);
            }
        } else {
            System.out.println("No properties found.");
        }
    }


    private void addPropertyItem(Property property) {
        try {
            // Load the property item FXML
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/loborems/PropertyListing/property-item.fxml")
            );
            Node propertyCard = loader.load();

            PropertyItemController itemController = loader.getController();
            itemController.setPropertyDetails(property);

            propertyListContainer.getChildren().add(propertyCard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Handle click to navigate to Add Property Page
    @FXML
    public void handleButtonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/loborems/AddProperty/add-property.fxml"));
            Parent root = loader.load();

            AddPropertyController controller = loader.getController();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 750);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handle Back Button click to return to Dashboard
    @FXML
    public void handleBackClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
