package com.example.loborems;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;


public class ClientPreferencesController {

    @FXML
    private Button downloadButton; // Define the downloadButton

    @FXML
    private ComboBox<String> activityLevelComboBox;

    @FXML
    private ComboBox<String> propertyTypeComboBox;

    @FXML
    private ComboBox<String> budgetRangeComboBox;

    @FXML
    private TableView<Client> clientsTableView;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> activityLevelColumn;

    @FXML
    private TableColumn<Client, String> propertyPreferencesColumn;

    @FXML
    private TableColumn<Client, String> budgetRangeColumn;

    @FXML
    private TableColumn<Client, Integer> recentInteractionsColumn;

    private final ObservableList<Client> clientList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize ComboBoxes
        activityLevelComboBox.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));
        propertyTypeComboBox.setItems(FXCollections.observableArrayList("Apartment", "Townhouse"));
        budgetRangeComboBox.setItems(FXCollections.observableArrayList("$300k-$500k", "$500k-$700k", "$700k-$900k"));

        // Initialize Table Columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        activityLevelColumn.setCellValueFactory(new PropertyValueFactory<>("activityLevel"));
        propertyPreferencesColumn.setCellValueFactory(new PropertyValueFactory<>("propertyPreferences"));
        budgetRangeColumn.setCellValueFactory(new PropertyValueFactory<>("budgetRange"));
        recentInteractionsColumn.setCellValueFactory(new PropertyValueFactory<>("recentInteractions"));

        // Load Data
        loadData();

        // Bind Data to TableView
        clientsTableView.setItems(clientList);
    }

    private void loadData() {
        // Add sample data for testing purposes
        clientList.add(new Client("John Doe", "High", "Apartment", "$300k-$500k", 5));
        clientList.add(new Client("Jane Smith", "Medium", "Townhouse", "$500k-$700k", 3));
        // Add more sample data as needed
    }

    @FXML
    private void resetFilters() {
        activityLevelComboBox.getSelectionModel().clearSelection();
        propertyTypeComboBox.getSelectionModel().clearSelection();
        budgetRangeComboBox.getSelectionModel().clearSelection();
        // Clear other filters or reload data if necessary
    }

    @FXML
    private void goToDownloadData() {
        try {
            Stage stage = (Stage) downloadButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("DownloadData/download-data.fxml"));
            stage.setScene(new Scene(root, 600, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Define the Client class for TableView
    public static class Client {
        private final String name;
        private final String activityLevel;
        private final String propertyPreferences;
        private final String budgetRange;
        private final int recentInteractions;

        public Client(String name, String activityLevel, String propertyPreferences, String budgetRange, int recentInteractions) {
            this.name = name;
            this.activityLevel = activityLevel;
            this.propertyPreferences = propertyPreferences;
            this.budgetRange = budgetRange;
            this.recentInteractions = recentInteractions;
        }

        public String getName() {
            return name;
        }

        public String getActivityLevel() {
            return activityLevel;
        }

        public String getPropertyPreferences() {
            return propertyPreferences;
        }

        public String getBudgetRange() {
            return budgetRange;
        }

        public int getRecentInteractions() {
            return recentInteractions;
        }
    }
}
