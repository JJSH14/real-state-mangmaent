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

}
