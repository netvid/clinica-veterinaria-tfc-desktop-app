package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.MainApplication;
import com.clinica_veterinaria.modelo.Veterinario;
import com.clinica_veterinaria.repositorio.VeterinarioRepositorio;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
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

public class VeterinarioControlador implements Initializable {

    private VeterinarioRepositorio repositorio = new VeterinarioRepositorio();
    private String[] sectores = new String[]{"Odontologia","Desparasitacion"};

    // ================== ATRIBUTOS ==================
    @FXML
    private TableView<Veterinario> tbVeterinarios;

    @FXML
    private TableColumn<Veterinario,String> colDni;

    @FXML
    private TableColumn<Veterinario,String> colNombre;

    @FXML
    private TableColumn<Veterinario,String> colApellidos;

    @FXML
    private TableColumn<Veterinario,String> colSector;

    @FXML
    private TableColumn<Veterinario, Date> colFechaNac;

    // ================== COMPONENTES ==================

    @FXML
    private TextField txtFieldSearch;

    public void inicializarVeterinarios(){
        this.colDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        this.colSector.setCellValueFactory(new PropertyValueFactory<>("Sector"));
        this.colFechaNac.setCellValueFactory(new PropertyValueFactory<>("FechaNac"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarVeterinarios();
        cargarVeterinarios();
    }

    public void cargarVeterinarios(){
        this.tbVeterinarios.setItems(repositorio.getObservableListOfVeterinarios(""));
    }

    @FXML
    public void onKeyReleasedSearch(){
        String busqueda = txtFieldSearch.getText();

        ObservableList<Veterinario> veterinariosFiltro = repositorio.getObservableListOfVeterinarios(busqueda);
        this.tbVeterinarios.setItems(veterinariosFiltro);
    }


    @FXML
    public void onActionAgregarVeterinario() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("agregar-veterinarios.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();

        // Refrescar la tabla de veterinarios al cerrar la aplicacion.
        cargarVeterinarios();
    }


    @FXML
    public void onContextMenuEliminar(){
        Veterinario veterinarioSeleccionado = this.tbVeterinarios.getSelectionModel().getSelectedItem();
        this.repositorio.eliminarUsuario(veterinarioSeleccionado);

        cargarVeterinarios();
    }




}
