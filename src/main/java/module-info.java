module com.clinica_veterinaria{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.clinica_veterinaria.controlador to javafx.fxml;
    opens com.clinica_veterinaria.modelo to javafx.base;

    exports com.clinica_veterinaria;


}