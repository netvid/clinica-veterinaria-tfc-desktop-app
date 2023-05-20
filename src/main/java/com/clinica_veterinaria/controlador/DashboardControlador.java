package com.clinica_veterinaria.controlador;

import com.clinica_veterinaria.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardControlador implements Initializable {

    @FXML
    private BorderPane myBorderPane;

    @FXML
    public void onClickHome() throws IOException {
        GridPane grid = FXMLLoader.load(MainApplication.class.getResource("vista/vista-presentacion.fxml"));
        myBorderPane.setCenter(grid);
    }

    @FXML
    public void onClickVerVeterinarios() throws IOException {
        GridPane grid = FXMLLoader.load(MainApplication.class.getResource("vista/vista-veterinarios.fxml"));
        myBorderPane.setCenter(grid);
    }


    @FXML
    public void onClickVerClientes() throws IOException {
        GridPane grid = FXMLLoader.load(MainApplication.class.getResource("vista/vista-clientes.fxml"));
        myBorderPane.setCenter(grid);
    }

    @FXML
    public void onClickVerMascotas() throws IOException {
        GridPane grid = FXMLLoader.load(MainApplication.class.getResource("vista/vista-mascotas.fxml"));
        myBorderPane.setCenter(grid);
    }


    @FXML
    public void onClickVerCitas() throws IOException {
        GridPane grid = FXMLLoader.load(MainApplication.class.getResource("vista/vista-citas.fxml"));
        myBorderPane.setCenter(grid);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GridPane grid = null;
        try {
            grid = FXMLLoader.load(MainApplication.class.getResource("vista/vista-presentacion.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        myBorderPane.setCenter(grid);
    }
}
