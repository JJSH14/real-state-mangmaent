package com.example.loborems;

import com.example.loborems.modules.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
public class ClientListPageController {

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> idColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;

    @FXML
    private TableColumn<Client, String> phoneColumn;

    @FXML
    private TableColumn<Client, String> propertyColumn;

    @FXML
    private TableColumn<Client, String> roleColumn;

    @FXML
    private TableColumn<Client, Void> editColumn;

    @FXML
    private TableColumn<Client, Void> removeColumn;

    @FXML
    private TextField nameField, emailField, phoneField, propertyField, roleField, searchField;

    @FXML
    private Button addClientButton;

    private final ObservableList<Client> clientList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // ربط الأعمدة بالبيانات
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        propertyColumn.setCellValueFactory(cellData -> cellData.getValue().propertyProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());

        addEditButtonToTable();
        addDeleteButtonToTable();

        clientTable.setItems(clientList);

        // إضافة وظيفة البحث
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchClients(newValue));
    }

    // زر إضافة عميل
    @FXML
    private void onAddClientButtonClicked() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String property = propertyField.getText();
        String role = roleField.getText();

        if (!name.isEmpty()) {
            String newId = String.valueOf(clientList.size() + 1); // توليد ID تلقائي
            clientList.add(new Client(newId, name, email, phone, property, role));
            clearFields();
        }
    }

    // إضافة زر Edit لكل صف
    private void addEditButtonToTable() {
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setStyle("-fx-background-color: #14274d; -fx-text-fill: white;");
                editButton.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    populateFieldsForEdit(client);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        };

        editColumn.setCellFactory(cellFactory);
    }

    // إضافة زر Delete لكل صف
    private void addDeleteButtonToTable() {
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setStyle("-fx-background-color: #14274d; -fx-text-fill: white;");
                deleteButton.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    clientList.remove(client);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        };

        removeColumn.setCellFactory(cellFactory);
    }

    // ملء الحقول لتحرير العميل
    private void populateFieldsForEdit(Client client) {
        nameField.setText(client.getName());
        emailField.setText(client.getEmail());
        phoneField.setText(client.getPhone());
        propertyField.setText(client.getProperty());
        roleField.setText(client.getRole());

        addClientButton.setText("Update Client");
        addClientButton.setOnAction(event -> updateClient(client));
    }

    // تحديث بيانات العميل
    private void updateClient(Client client) {
        client.setName(nameField.getText());
        client.setEmail(emailField.getText());
        client.setPhone(phoneField.getText());
        client.setProperty(propertyField.getText());
        client.setRole(roleField.getText());

        clientTable.refresh();
        clearFields();
        addClientButton.setText("Add Client");
        addClientButton.setOnAction(event -> onAddClientButtonClicked());
    }

    // مسح الحقول بعد الإضافة أو التحديث
    private void clearFields() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        propertyField.clear();
        roleField.clear();
    }

    // البحث عن العملاء
    private void searchClients(String searchQuery) {
        ObservableList<Client> filteredList = FXCollections.observableArrayList();
        for (Client client : clientList) {
            if (client.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredList.add(client);
            }
        }
        clientTable.setItems(filteredList);

        if (searchQuery.isEmpty()) {
            clientTable.setItems(clientList); // إعادة القائمة الأصلية
        }
    }
}