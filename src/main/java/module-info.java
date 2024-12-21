module com.example.loborems {
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.sql;
    requires mysql.connector.java;
    requires java.naming;
    requires jbcrypt;
    requires org.slf4j;
    requires com.jfoenix;

    opens com.example.loborems;
    opens com.example.loborems.models;
    exports com.example.loborems;
    exports com.example.loborems.controller.authntociation;
    opens com.example.loborems.controller.authntociation to javafx.fxml;
}