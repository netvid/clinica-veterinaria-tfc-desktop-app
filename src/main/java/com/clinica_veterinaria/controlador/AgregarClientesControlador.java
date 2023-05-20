package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.interfaces.IClinicaForm;
import com.clinica_veterinaria.modelo.Cliente;
import com.clinica_veterinaria.repositorio.ClienteRepositorio;
import com.clinica_veterinaria.utiles.Utiles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AgregarClientesControlador implements IClinicaForm<Cliente> {
    private String modo = "";
    private Utiles utiles = new Utiles();
    private ClienteRepositorio repositorio = new ClienteRepositorio();

    private String dniInicial = "";

    @FXML
    private TextField textFieldDni;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldApellidos;

    @FXML
    private DatePicker datePickerFechaNac;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelar;



    @FXML
    public void onClickAgregar(){
        String dni = this.textFieldDni.getText();
        String nombre = this.textFieldNombre.getText();
        String apellidos = this.textFieldApellidos.getText();
        Date fechaNac = Date.valueOf(this.datePickerFechaNac.getValue());

        Cliente cliente = new Cliente(dni,nombre,apellidos,fechaNac);

        switch(this.modo){
            case "editar":
                this.repositorio.actualizar(cliente,dniInicial);
                break;
            default:
                this.repositorio.create(cliente);
                break;
        }

        this.utiles.cerrarVentanaPorBoton(btnAgregar);
    }

    @FXML
    public void onClickCancelar(){
        this.utiles.cerrarVentanaPorBoton(btnCancelar);
    }

    @Override
    public void iniciarAtributos(Cliente entidad) {
        switch(modo){

            case "editar":
                this.dniInicial = entidad.getDni();
                this.textFieldDni.setText(entidad.getDni());
                this.textFieldNombre.setText(entidad.getNombre());
                this.textFieldApellidos.setText(entidad.getApellidos());
                this.datePickerFechaNac.setValue(this.utiles.toLocalDate(entidad.getFechaNac()));
                break;
        }
    }


    public void setModo(String modo){
        this.modo = modo;
    }
}
