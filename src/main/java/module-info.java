module com.example.loborems {
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;

    opens com.example.loborems to javafx.fxml;
    exports com.example.loborems;
}