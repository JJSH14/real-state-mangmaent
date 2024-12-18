package com.example.loborems;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class forget_controler {
    public void gotoreset(ActionEvent event) throws IOException {
        Parent secondRoot = FXMLLoader.load(getClass().getResource("reset-password.fxml"));
        Scene newScene = new Scene(secondRoot);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(newScene);

    }
}
