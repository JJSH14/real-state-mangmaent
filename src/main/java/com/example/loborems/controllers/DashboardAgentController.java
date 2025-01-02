package com.example.loborems.controllers;
import com.example.loborems.models.Interfaces.PropertyDAO;
import com.example.loborems.models.Interfaces.UserDOA;
import com.example.loborems.models.Property;
import com.example.loborems.models.User;
import com.example.loborems.models.services.UserDOAimp;
import com.example.loborems.models.services.PropertyDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class DashboardAgentController implements Initializable {
    @FXML
    private Text total;
    @FXML
    private Text active;
    @FXML
    private Text name;
    @FXML
    private Text email;
    @FXML
    private Text proname;
    @FXML
    private Text prostatus;
    @FXML
    private Text price;
    @FXML
    private Text locationn;

    private final UserDOA userDao;
    private final PropertyDAO propertyDao;

    public DashboardAgentController() {
        this.userDao = new UserDOAimp(); // Or use dependency injection if available
        this.propertyDao = new PropertyDAOImpl(); // Or use dependency injection if available
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Retrieve all properties and count them
            List<Property> properties = propertyDao.getAllProperties();
            total.setText(String.valueOf(properties.size()));




            // Retrieve all users and count them
            List<User> users = userDao.getAll();
            active.setText(String.valueOf(users.size()));

            // Set the first user's details (assuming it's an agent)
            if (!users.isEmpty()) {
                User user = users.get(0); // Get the first user
                name.setText(user.getFullName());
                email.setText(user.getEmail());
            }

            // Set the first property's details
            if (!properties.isEmpty()) {
                Property property = properties.get(0); // Get the first property
                proname.setText(property.getFeatures());
                price.setText(String.valueOf(property.getPrice()));
                locationn.setText(property.getLocation());
                prostatus.setText(property.getStatus());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/Login/login.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }

    public void goToDashboard(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/Dashboard/dashboard.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }

    public void goToInteractions(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/ClientInteraction/client-interaction.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }

    public void goToClients(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/ClientList/client-list.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }

    public void goToProperties(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/PropertyListing/property-listing.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }

    public void goToAddAgents(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/AddAgent/addAgent.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }

    public void goToCategorisation(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/ClientCategorization/client-categorization.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }

    public void goToOffers(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/Offers/offers.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }

    public void goToDownloadData(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/com/example/loborems/DownloadData/download-data.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);
    }
}
