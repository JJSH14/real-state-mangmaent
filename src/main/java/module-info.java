module com.example.loborems {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires mysql.connector.java;
    requires java.persistence;


    // Export and open necessary packages


    opens com.example.loborems to javafx.fxml;
    exports com.example.loborems;
    exports com.example.loborems.controllers;
    exports com.example.loborems.interfaces;
    opens com.example.loborems.interfaces to javafx.fxml;
    opens com.example.loborems.models to javafx.fxml, org.hibernate.orm.core;  // Open to both javafx.fxml and Hibernate;
    exports com.example.loborems.models;
    opens com.example.loborems.util to javafx.fxml;
    exports com.example.loborems.util;
    opens com.example.loborems.controllers to javafx.fxml;
}