package com.example.loborems;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class dashboord {
    public void logout(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);


    }

    public void goToDashboard(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }
    public void goToInteractions(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("ClientInteractionUi/ClaientIntersction.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }

    public void goToClients(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("ClientListPage.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }

    public void goToProperties(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("property-listing.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }

    public void goToAddAgents(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("addAgent.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }
    public void goToCategorisation(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("Client-Categorization.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }
    public void goToOffers(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("offer-page.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }
    public void goToDownloadData(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("DownloadData/download-data.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }


}

