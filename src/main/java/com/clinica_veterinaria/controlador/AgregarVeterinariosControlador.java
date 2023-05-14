package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.modelo.Veterinario;
import com.clinica_veterinaria.repositorio.VeterinarioRepositorio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AgregarVeterinariosControlador implements Initializable {

    private VeterinarioRepositorio repositorio = new VeterinarioRepositorio();
    private String[] sectores = new String[]{"Odontologia","Vacunacion"};

    @FXML
    TextField textFieldDni;

    @FXML
    TextField textFieldNombre;

    @FXML
    TextField textFieldApellidos;

    @FXML
    DatePicker textFieldFechaNac;

    @FXML
    Button btnAgregar;

    @FXML
    Button btnCancelar;

    @FXML
    private ChoiceBox<String> myChoiceBox;


    @FXML
    public void onClickAgregar() {
        String dni = textFieldDni.getText();
        String nombre = textFieldNombre.getText();
        String apellidos = textFieldApellidos.getText();
        String sector = myChoiceBox.getValue();

        LocalDate localDate = textFieldFechaNac.getValue();
        Date fecha = Date.valueOf(localDate);

        Veterinario veterinarioNuevo = new Veterinario(dni,nombre,apellidos,sector,fecha);
        repositorio.insertarVeterinario(veterinarioNuevo);

        cerrarVentana(btnAgregar);
    }

    @FXML
    public void onClickCancelar(){
        cerrarVentana(btnCancelar);
    }

    public void cerrarVentana(Button btn){
        Stage escenarioActual = (Stage) btn.getScene().getWindow();
        escenarioActual.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.myChoiceBox.getItems().addAll(sectores);

    }
}
