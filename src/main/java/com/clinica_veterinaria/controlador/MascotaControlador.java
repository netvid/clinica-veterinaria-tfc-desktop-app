package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.MainApplication;
import com.clinica_veterinaria.modelo.Mascota;
import com.clinica_veterinaria.repositorio.MascotaRepositorio;
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
import java.util.ResourceBundle;

public class MascotaControlador implements Initializable {

    private MascotaRepositorio repositorio = new MascotaRepositorio();
    private Utiles utiles = new Utiles();

    private MisAlertas alertas = new MisAlertas();


    // ================== ATRIBUTOS ==================
    @FXML
    private TableView<Mascota> tbMascotas;

    @FXML
    private TableColumn<Mascota,String> colChip;

    @FXML
    private TableColumn<Mascota,String> colNombre;

    @FXML
    private TableColumn<Mascota,String> colTipo;

    @FXML
    private TableColumn<Mascota,String> colRaza;

    @FXML
    private TableColumn<Mascota,String> colDniCliente;

    @FXML
    private TextField txtFieldSearch;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarColumnas();
        cargarTabla();
    }


    @FXML
    public void onKeyReleasedSearch(){
        String busqueda = txtFieldSearch.getText();
        this.tbMascotas.setItems(repositorio.obtenerTodos(busqueda));
    }

    @FXML
    public void onActionAgregarMascotas(){
        this.utiles.abrirVentana("","agregar-mascotas","showAndWait");
        cargarTabla();
    }

    @FXML
    public void onContextMenuEliminar(){
        Mascota mascotaSeleccionada = this.tbMascotas.getSelectionModel().getSelectedItem();
        this.repositorio.borrar(mascotaSeleccionada.getChip());
        cargarTabla();
    }

    @FXML
    public void onContextMenuEditar() throws IOException {
        Mascota mascotaSeleccionada = this.tbMascotas.getSelectionModel().getSelectedItem();
        editarForm(mascotaSeleccionada);
        cargarTabla();
    }

    public void editarForm(Mascota mascota) throws IOException {
        FXMLLoader loader =  new FXMLLoader(MainApplication.class.getResource("vista/agregar-mascotas.fxml"));
        Scene scene = new Scene(loader.load()) ;
        Stage stage  = new Stage();

        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        AgregarMascotasControlador controlador = loader.getController();
        controlador.setModo("editar");
        controlador.iniciarAtributos(mascota);


        stage.showAndWait();
        cargarTabla();
    }

    public void iniciarColumnas(){
        this.colChip.setCellValueFactory(new PropertyValueFactory<>("Chip"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.colTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        this.colRaza.setCellValueFactory(new PropertyValueFactory<>("Raza"));
        this.colDniCliente.setCellValueFactory(new PropertyValueFactory<>("DniCliente"));
    }

    public void cargarTabla(){
        this.tbMascotas.setItems(repositorio.obtenerTodos(""));
        this.tbMascotas.refresh();
    }
}
