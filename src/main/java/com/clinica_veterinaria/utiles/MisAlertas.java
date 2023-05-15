package com.clinica_veterinaria.utiles;

import javafx.scene.control.Alert;

public class MisAlertas {

    public void crearAlerta(String titulo, String header, Alert.AlertType type){
        Alert alerta = new Alert(type);
        alerta.setTitle(titulo);
        alerta.setHeaderText(header);
        alerta.showAndWait();
    }
}
