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

    @FXML
    public Button navigateButton;
    @FXML
    public Button backButton;
    @FXML
    public Button detailsPage;
    @FXML
    private FlowPane propertyListContainer;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;


    @FXML
    private PropertyService propertyService; // Assuming this is properly initialized elsewhere

    private List<Property> properties;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize any required logic here (e.g., populate property list)
        System.out.println("Initializing property listing...");

        if (propertyService != null) {
            // Fetch all properties from the service
            System.out.println("Property service is available, fetching properties...");
            properties = propertyService.getAllProperties();

            if (properties != null && !properties.isEmpty()) {
                System.out.println("Found " + properties.size() + " properties.");

                // Add each property item using the addPropertyItem method
                for (Property property : properties) {
                    System.out.println("Adding property: " + property.getTitle());
                    addPropertyItem(property.getTitle(), property.getLocation(), property.getType(), property.getPrice(), property.getSize());
                }
            } else {
                System.out.println("No properties found in the service.");
            }
        } else {
            System.out.println("Property service is not available.");
        }
    }



    private void addPropertyItem(String title, String location, String type, double price, double size) {
        try {
            // Load the property item FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/loborems/PropertyListing/property-item.fxml"));
            Node propertyCard = loader.load();

            // Get the controller and set the property details
            PropertyItemController itemController = loader.getController();

            // Create a property object based on the type
            Property property = null;
            if ("Residential".equals(type)) {
                property = new ResidentialProperty();
            } else if ("Commercial".equals(type)) {
                property = new CommercialProperty();
            }

            if (property != null) {
                property.setTitle(title);
                property.setLocation(location);
                property.setType(type);
                property.setPrice(price);
                property.setSize(size);
                itemController.setPropertyDetails(property);  // Set property details in the controller
            }

            // Add the property card to the FlowPane
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
