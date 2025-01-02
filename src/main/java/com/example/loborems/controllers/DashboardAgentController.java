package com.example.loborems.controllers;

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
import java.sql.*;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

//import java.sql.Connection;
public class DashboardAgentController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/LoboDB";
    private static final String USER = "root"; // اسم المستخدم الخاص بـ MySQL
    private static final String PASSWORD = ""; // كلمة المرور (اتركها فارغة إذا لم تضف كلمة مرور)
    @FXML
    Text total;
    @FXML
    Text active;
    @FXML
    Text name;
    @FXML
    Text email;
    @FXML
    Text proname;
    @FXML
    Text prostatus;
    @FXML
    Text price;
    @FXML
    Text locationn;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection h2=null;
        Connection aa = null;
        try {
            h2= DriverManager.getConnection(URL, USER, PASSWORD);
            aa = DriverManager.getConnection(URL, USER, PASSWORD);
            String hh = "SELECT count(id) from property ";
            Statement stmt = h2.createStatement();
            ResultSet rs = stmt.executeQuery(hh);
            if (rs.next()) {
                int count = rs.getInt(1); // الحصول على القيمة من العمود الأول
                total.setText(valueOf(count));
            } else {
                System.out.print("No results found.");
            }

            String sql="SELECT count(id) from client ";
            Statement stmtt = aa.createStatement();
            ResultSet rss = stmtt.executeQuery(sql);
            if (rss.next()) {
                int countt = rss.getInt(1); // الحصول على القيمة من العمود الأول
                active.setText(valueOf(countt));
            } else {
                System.out.print("No results found.");
            }

            String sql1="SELECT * from client";
            ResultSet rsse = stmtt.executeQuery(sql1);
            if(rsse.next()){
                String namee=rsse.getString("name");
                String emaill=rsse.getString("email");
                name.setText(namee);
                email.setText(emaill);

            }

            String sql3="SELECT * from property";
            Statement stmt3 = h2.createStatement();
            ResultSet rss3 = stmt3.executeQuery(sql3);
            if(rss3.next()){
                String profeat= rss3.getString("features");
                String prolocation= rss3.getString("location");
                int proprice= rss3.getInt("price");
                String status= rss3.getString("status");
                proname.setText(profeat);
                price.setText(valueOf(proprice));
                locationn.setText(prolocation);
                prostatus.setText(status);

            }




        } catch (SQLException e) {
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

