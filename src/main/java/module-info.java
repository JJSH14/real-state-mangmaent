module com.example.loborems {
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires mysql.connector.java;
    requires java.persistence;
    // Export and open necessary packages
    opens com.example.loborems.modules to javafx.fxml, org.hibernate.orm.core;
    exports com.example.loborems.modules;
    opens com.example.loborems to javafx.fxml;
    exports com.example.loborems;
    opens com.example.loborems.utile to javafx.fxml;
    exports com.example.loborems.utile;
    opens com.example.loborems.interfaces to javafx.fxml;
    exports com.example.loborems.interfaces;
}