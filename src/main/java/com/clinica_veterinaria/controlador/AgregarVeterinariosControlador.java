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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AgregarVeterinariosControlador implements Initializable {

    private VeterinarioRepositorio repositorio = new VeterinarioRepositorio();
    private String[] sectores = new String[]{"Odontologia","Vacunacion","Esterilizacion","Desparasitacion","Identificacion"};

    private String modo = "";

    private Veterinario veterinarioEditar;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.myChoiceBox.getItems().addAll(sectores);
    }

    @FXML
    public void onClickAgregar() {
        String dni = textFieldDni.getText();
        String nombre = textFieldNombre.getText();
        String apellidos = textFieldApellidos.getText();
        String sector = myChoiceBox.getValue();
        LocalDate localDate = textFieldFechaNac.getValue();
        Date fecha = Date.valueOf(localDate);
        Veterinario veterinario = new Veterinario(dni,nombre,apellidos,sector,fecha);

        switch(modo){
            case "editar":
                repositorio.actualizarVeterinario(veterinario);
                break;
            default:
                repositorio.insertarVeterinario(veterinario);
        }
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

    /**
     * Se asignan como valores de las entradas de texto TextField los valoren de un Objeto Veterinario.
     */
    public void iniciarAtributos(){
        switch(modo){
            case "editar":
                this.textFieldDni.setText(veterinarioEditar.getDni());
                this.textFieldNombre.setText(veterinarioEditar.getNombre());
                this.textFieldApellidos.setText(veterinarioEditar.getApellidos());
                this.myChoiceBox.setValue(veterinarioEditar.getSector());
                Date fechaNac = veterinarioEditar.getFechaNac();

                // Convertir una fecha de tipo date a local date.
                LocalDate localDate = Instant.ofEpochMilli(fechaNac.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
                this.textFieldFechaNac.setValue(localDate);
                break;
        }
    }

    /**
     * En funcion de la cadena abre una ventana para insertar o actualizar un veterinario.
     * @param modo
     */
    public void setModo(String modo){
        this.modo = modo;
    }


    public void setVeterinarioEditar(Veterinario veterinarioEditar){
        this.veterinarioEditar = veterinarioEditar;
    }
}
