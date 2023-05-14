package com.clinica_veterinaria.utiles;

import javafx.scene.control.Alert;

public class MisAlertas {

    public void crearAlerta(String titulo, String header, String tipo){
        Alert alerta = null;

        switch(tipo){
            case "error":
                alerta = new Alert(Alert.AlertType.ERROR);
                break;
            case "advertencia":
                alerta = new Alert(Alert.AlertType.WARNING);
                break;
            case "confirmacion":
                alerta = new Alert(Alert.AlertType.CONFIRMATION);
                break;
        }
    }
}
