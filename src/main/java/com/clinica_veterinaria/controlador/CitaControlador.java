package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.interfaces.IClinica;
import com.clinica_veterinaria.modelo.Cita;
import com.clinica_veterinaria.repositorio.CitaRepositorio;
import com.clinica_veterinaria.utiles.MisAlertas;
import com.clinica_veterinaria.utiles.Utiles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class CitaControlador implements IClinica, Initializable {
    private CitaRepositorio repositorio = new CitaRepositorio();
    private Utiles utiles  = new Utiles();

    private MisAlertas alertas = new MisAlertas();

    @FXML
    private TableView<Cita> tbCitas;

    @FXML
    private TableColumn<Cita,Long> colId;

    @FXML
    private TableColumn<Cita, Date> colFecha;

    @FXML
    private TableColumn<Cita,String> colDniCliente;

    @FXML
    private TableColumn<Cita,String> colDniVeterinario;

    @FXML
    private TableColumn<Cita,String> colChipMascota;

    @FXML
    public void onKeyReleasedSearch(){

    }

    @FXML
    public void onActionAgregarCitas(){

    }

    @FXML
    public void onContextMenuEliminar(){

    }

    @FXML
    public void onContextMenuEditar(){

    }

    @Override
    public void iniciarColumnas() {
        this.colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        this.colDniCliente.setCellValueFactory(new PropertyValueFactory<>("DniCliente"));
        this.colDniVeterinario.setCellValueFactory(new PropertyValueFactory<>("DniVeterinario"));
        this.colChipMascota.setCellValueFactory(new PropertyValueFactory<>("ChipMascota"));
    }

    @Override
    public void cargarTabla() {
        this.tbCitas.setItems(this.repositorio.obtenerTodos(""));
        this.tbCitas.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.iniciarColumnas();
        this.cargarTabla();
    }
}
