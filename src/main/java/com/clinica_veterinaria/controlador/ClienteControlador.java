package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.MainApplication;
import com.clinica_veterinaria.interfaces.IClinica;
import com.clinica_veterinaria.modelo.Cliente;
import com.clinica_veterinaria.repositorio.ClienteRepositorio;
import com.clinica_veterinaria.utiles.MisAlertas;
import com.clinica_veterinaria.utiles.Utiles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class ClienteControlador implements IClinica, Initializable {


    private ClienteRepositorio repositorio = new ClienteRepositorio();

    private Utiles utiles = new Utiles();

    private MisAlertas alertas = new MisAlertas();

    @FXML
    private TableView<Cliente> tbClientes;


    @FXML
    private TableColumn<Cliente,String> colDni;

    @FXML
    private TableColumn<Cliente,String> colNombre;

    @FXML
    private TableColumn<Cliente,String> colApellidos;

    @FXML
    private TableColumn<Cliente, Date> colFechaNac;

    @FXML
    private TextField txtFieldSearch;


    @FXML
    public void onKeyReleasedSearch(){
        String busqueda = this.txtFieldSearch.getText();
        this.tbClientes.setItems(repositorio.obtenerTodos(busqueda));
    }

    @FXML
    public void onActionAgregarClientes(){
        this.utiles.abrirVentana("","agregar-clientes","showAndWait");
        this.cargarTabla();
    }

    @FXML
    public void onContextMenuEliminar(){
        Cliente clienteSeleccionado = this.tbClientes.getSelectionModel().getSelectedItem();
        this.repositorio.borrar(clienteSeleccionado.getDni());
        this.cargarTabla();
    }

    @FXML
    public void onContextMenuEditar(){
        abrirFormEditar();
        cargarTabla();
    }

    public void abrirFormEditar() {
        Cliente clienteSeleccionado = this.tbClientes.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("vista/agregar-clientes.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            this.alertas.crearAlerta("Error",e.toString(), Alert.AlertType.ERROR);
        }

        Stage stage = new Stage();

        AgregarClientesControlador controlador = loader.getController();
        controlador.setModo("editar");
        controlador.iniciarAtributos(clienteSeleccionado);

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @Override
    public void iniciarColumnas() {
        this.colDni.setCellValueFactory(new PropertyValueFactory<>("Dni"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
        this.colFechaNac.setCellValueFactory(new PropertyValueFactory<>("FechaNac"));
    }

    @Override
    public void cargarTabla() {
        this.tbClientes.setItems(this.repositorio.obtenerTodos(""));
        this.tbClientes.refresh();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.iniciarColumnas();
        this.cargarTabla();
    }


}
