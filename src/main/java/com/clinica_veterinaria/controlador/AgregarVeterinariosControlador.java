package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.interfaces.IClinicaForm;
import com.clinica_veterinaria.modelo.Veterinario;
import com.clinica_veterinaria.repositorio.VeterinarioRepositorio;
import com.clinica_veterinaria.utiles.Utiles;
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

public class AgregarVeterinariosControlador implements Initializable, IClinicaForm<Veterinario> {

    private VeterinarioRepositorio repositorio = new VeterinarioRepositorio();
    private Utiles utiles = new Utiles();
    private String[] sectores = new String[]{"Odontologia","Vacunacion","Esterilizacion","Desparasitacion","Identificacion"};

    private String modo = "";


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
                repositorio.actualizar(veterinario);
                break;
            default:
                repositorio.create(veterinario);
        }

        this.utiles.cerrarVentanaPorBoton(btnAgregar);
    }

    @FXML
    public void onClickCancelar(){
        this.utiles.cerrarVentanaPorBoton(btnCancelar);
    }


    /**
     * Se asignan como valores de las entradas de texto TextField los valoren de un Objeto Veterinario.
     */
    @Override
    public void iniciarAtributos(Veterinario veterinario){
        switch(modo){
            case "editar":
                this.textFieldDni.setText(veterinario.getDni());
                this.textFieldNombre.setText(veterinario.getNombre());
                this.textFieldApellidos.setText(veterinario.getApellidos());
                this.myChoiceBox.setValue(veterinario.getSector());
                Date fechaNac = veterinario.getFechaNac();

                this.textFieldFechaNac.setValue(this.utiles.toLocalDate(fechaNac));
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

}
