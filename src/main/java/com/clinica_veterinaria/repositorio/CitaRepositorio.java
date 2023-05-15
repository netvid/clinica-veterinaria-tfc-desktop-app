package com.clinica_veterinaria.repositorio;

import com.clinica_veterinaria.conexion.Conexion;
import com.clinica_veterinaria.interfaces.IDao;
import com.clinica_veterinaria.modelo.Cita;
import com.clinica_veterinaria.utiles.MisAlertas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import javax.crypto.AEADBadTagException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CitaRepositorio implements IDao<Cita,Long> {
    private String tableName = "citas";
    private String pkName = "cita_id";

    private MisAlertas alertas = new MisAlertas();

    @Override
    public void create(Cita entity) {

    }

    @Override
    public ObservableList<Cita> obtenerTodos(String busqueda) {
        ObservableList<Cita> citas = FXCollections.observableArrayList();
        String query = !busqueda.equals("") ? String.format("SELECT * FROM %s where %s like '%%s%'",tableName,pkName,busqueda) : "SELECT * FROM " + tableName;

        Conexion con = new Conexion();
        try{
            ResultSet rs = con.ejecutarConsulta(query);
            while(rs.next()){
                Long id = rs.getLong("cita_id");
                Date fecha = rs.getDate("cita_fecha");
                String dniCliente = rs.getString("cli_dni");
                String dniVeterinario = rs.getString("vet_dni");
                String chipMascota = rs.getString("masc_chip");

                Cita cita = new Cita(id,fecha,dniCliente,dniVeterinario,chipMascota);
                citas.add(cita);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            this.alertas.crearAlerta("Error",e.toString(), Alert.AlertType.ERROR);
        }

        return citas;
    }

    @Override
    public void actualizar(Cita entity) {

    }

    @Override
    public void borrar(Long id) {

    }
}
