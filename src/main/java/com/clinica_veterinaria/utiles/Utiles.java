package com.clinica_veterinaria.utiles;

import com.clinica_veterinaria.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utiles {
    private MisAlertas alertas = new MisAlertas();

    public void abrirVentana(String titulo, String ventana, String modoAbrir) {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("vista/" + ventana + ".fxml"));
        Scene scene = null;

        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            alertas.crearAlerta("Error",e.toString(),"error");
        }

        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        switch(modoAbrir){
            case "showAndWait":
                stage.showAndWait();
                break;
            default:
                stage.show();
        }
    }

    public void cerrarVentanaPorBoton(Button btn){
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public LocalDate toLocalDate(Date date){
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
