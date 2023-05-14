package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.interfaces.IClinicaForm;
import com.clinica_veterinaria.modelo.Mascota;
import com.clinica_veterinaria.repositorio.MascotaRepositorio;
import com.clinica_veterinaria.utiles.Utiles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarMascotasControlador implements Initializable, IClinicaForm<Mascota> {

    private MascotaRepositorio repositorio = new MascotaRepositorio();
    private Utiles utiles = new Utiles();
    private String modo = "";

    private String tipos[] = new String[]{"Perro","Gato","Raton","Ardilla","Tortuga"};

    @FXML
    private TextField textFieldChip;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private ChoiceBox<String> choiceBoxTipo;

    @FXML
    private TextField textFieldRaza;

    @FXML
    private TextField textFieldDniCliente;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelar;


    @FXML
    public void onClickCancelar(){
        this.utiles.cerrarVentanaPorBoton(btnCancelar);
    }


    @FXML
    public void onClickAgregar(){
        String chip = textFieldChip.getText();
        String nombre = textFieldNombre.getText();
        String tipo = choiceBoxTipo.getValue();
        String raza = textFieldRaza.getText();
        String dniCliente = textFieldDniCliente.getText();

        Mascota mascota = new Mascota(chip,nombre,tipo,raza,dniCliente);

        switch(modo){
            case "editar":
                this.repositorio.actualizar(mascota);
                break;
            default:
                this.repositorio.create(mascota);
                break;
        }

        this.utiles.cerrarVentanaPorBoton(btnAgregar);
    }

    @Override
    public void iniciarAtributos(Mascota mascota) {
        switch(modo){
            case "editar":
                this.textFieldChip.setText(mascota.getChip());
                this.textFieldNombre.setText(mascota.getNombre());
                this.choiceBoxTipo.setValue(mascota.getTipo());
                this.textFieldRaza.setText(mascota.getRaza());
                this.textFieldDniCliente.setText(mascota.getDniCliente());
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.choiceBoxTipo.getItems().addAll(tipos);
    }

    public void setModo(String modo){
        this.modo = modo;
    }
}
