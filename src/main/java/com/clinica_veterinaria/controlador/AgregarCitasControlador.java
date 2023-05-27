package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.interfaces.IClinicaForm;
import com.clinica_veterinaria.modelo.Cita;
import com.clinica_veterinaria.repositorio.CitaRepositorio;
import com.clinica_veterinaria.utiles.MisAlertas;
import com.clinica_veterinaria.utiles.Utiles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AgregarCitasControlador implements IClinicaForm<Cita>{
    private String modo = "";
    private CitaRepositorio repositorio = new CitaRepositorio();
    private Utiles utiles = new Utiles();
    private MisAlertas alertas = new MisAlertas();

    private Long idInicial = 1L;

    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private TextField textFieldHora;

    @FXML
    private TextField textFieldDniCliente;

    @FXML
    private TextField textFieldDniVeterinario;

    @FXML
    private TextField textFieldChipMascota;


    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelar;



    @FXML
    public void onClickAgregar(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String dateFormated = formatter.format(datePickerFecha.getValue());
        String sFecha = dateFormated;
        String sHora = this.textFieldHora.getText();

        String sFechaHora = sFecha + " " +  sHora;

        String dniCliente = this.textFieldDniCliente.getText();
        String dniVeterinario = this.textFieldDniVeterinario.getText();
        String chipMascota = this.textFieldChipMascota.getText();

        Cita cita = new Cita(sFechaHora,dniCliente, dniVeterinario, chipMascota);
        switch(modo){
            case "editar":
                this.repositorio.actualizar(cita,idInicial);
                break;
            default:
                this.repositorio.create(cita);
                break;
        }

        this.utiles.cerrarVentanaPorBoton(btnAgregar);
    }

    @FXML
    public void onClickCancelar(){
        this.utiles.cerrarVentanaPorBoton(btnCancelar);
    }

    @Override
    public void iniciarAtributos(Cita entidad) {
        switch(modo){
            case "editar":
                this.idInicial = entidad.getId();
                this.textFieldDniCliente.setText(entidad.getDniCliente());
                this.textFieldDniVeterinario.setText(entidad.getDniVeterinario());
                this.textFieldChipMascota.setText(entidad.getChipMascota());
                break;
        }
    }

    public void setModo(String modo){
        this.modo = modo;
    }


}
