package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.MainApplication;
import com.clinica_veterinaria.interfaces.IClinica;
import com.clinica_veterinaria.modelo.Cita;
import com.clinica_veterinaria.repositorio.CitaRepositorio;
import com.clinica_veterinaria.utiles.MisAlertas;
import com.clinica_veterinaria.utiles.Utiles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TextField txtFieldSearch;

    @FXML
    public void onKeyReleasedSearch(){
        String input = this.txtFieldSearch.getText();
        this.tbCitas.setItems(this.repositorio.obtenerTodos(input));
        this.tbCitas.refresh();
    }

    @FXML
    public void onActionAgregarCitas(){
        this.utiles.abrirVentana("","agregar-citas","showAndWait");
        cargarTabla();
    }

    @FXML
    public void onContextMenuEliminar(){
        Cita citaSeleccionada = this.tbCitas.getSelectionModel().getSelectedItem();
        this.repositorio.borrar(citaSeleccionada.getId());
        cargarTabla();
    }

    @FXML
    public void onContextMenuEditar() throws IOException {
        abrirForm();
        cargarTabla();
    }

    public void abrirForm() throws IOException {
        Cita citaSeleccionada = this.tbCitas.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("vista/agregar-citas.fxml"));
        Scene scene = new Scene(loader.load());

        AgregarCitasControlador controlador = loader.getController();
        controlador.setModo("editar");
        controlador.iniciarAtributos(citaSeleccionada);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

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
