package com.example.loborems;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DownloadDataController {

    @FXML
    private TextField clientIDTextField;

    @FXML
    private TableView<ClientData> clientInfoTable;

    @FXML
    private TableColumn<ClientData, String> fieldColumn;

    @FXML
    private TableColumn<ClientData, String> valueColumn;

    @FXML
    private Button downloadButton;

    @FXML
    private ProgressIndicator downloadProgress;

    private final ObservableList<ClientData> clientDataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize Table Columns
        fieldColumn.setCellValueFactory(new PropertyValueFactory<>("field"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        // Bind Data to TableView
        clientInfoTable.setItems(clientDataList);
    }

    @FXML
    private void searchClient() {
        // Perform search based on client ID and populate table
        String clientID = clientIDTextField.getText();
        if (clientID != null && !clientID.trim().isEmpty()) {
            // Fetch client data (dummy data for example)
            clientDataList.clear();
            clientDataList.add(new ClientData("Name", "John Doe"));
            clientDataList.add(new ClientData("Email", "john.doe@example.com"));
            // Enable download button
            downloadButton.setDisable(false);
        }
    }

    @FXML
    private void downloadData() {
        // Start download process
        downloadProgress.setVisible(true);
        downloadButton.setDisable(true);

        // Simulate download process
        new Thread(() -> {
            try {
                Thread.sleep(2000); // Simulate time-consuming task
                // Hide progress indicator
                downloadProgress.setVisible(false);
                // Enable download button
                downloadButton.setDisable(false);
                // Notify user of successful download (implement actual notification logic)
                System.out.println("Data downloaded successfully!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    private void goToPreferences() {
        try {
            Stage stage = (Stage) downloadButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("ClientPreferences/client-preferences.fxml"));

            stage.setScene(new Scene(root, 600, 500));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Define the ClientData class for TableView
    public static class ClientData {
        private final String field;
        private final String value;

        public ClientData(String field, String value) {
            this.field = field;
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public String getValue() {
            return value;
        }
    }
}
