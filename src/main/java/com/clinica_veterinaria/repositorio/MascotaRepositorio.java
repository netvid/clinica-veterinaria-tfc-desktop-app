package com.clinica_veterinaria.repositorio;

import com.clinica_veterinaria.conexion.Conexion;
import com.clinica_veterinaria.interfaces.IDao;
import com.clinica_veterinaria.modelo.Mascota;
import com.clinica_veterinaria.utiles.MisAlertas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MascotaRepositorio implements IDao<Mascota,String> {

    private MisAlertas alertas = new MisAlertas();

    @Override
    public void create(Mascota entity) {
        String query = "INSERT INTO Mascotas values (" +
                "'" + entity.getChip() + "'" + "," +
                "'" + entity.getNombre() + "'" + "," +
                "'" + entity.getTipo() + "'" + "," +
                "'" + entity.getRaza() + "'" + "," +
                "'" + entity.getDniCliente() + "'" + ");";

        Conexion con = new Conexion();
        con.ejecutarActualizacion(query);
        con.close();
    }

    @Override
    public ObservableList<Mascota> obtenerTodos(String busqueda){
        ObservableList<Mascota> mascotas = FXCollections.observableArrayList();

        String query = "";
        Conexion con = new Conexion();

        if(!busqueda.equals("")) query = "SELECT * FROM Mascotas where masc_chip like " + "'%" + busqueda + "%'";
        else query = "SELECT * FROM Mascotas";

        ResultSet rs = con.ejecutarConsulta(query);
        try{
            while(rs.next()){
                String chip = rs.getString("masc_chip");
                String nombre = rs.getString("masc_nombre");
                String tipo = rs.getString("masc_tipo");
                String raza = rs.getString("masc_raza");
                String dniCliente = rs.getString("cli_dni");

                Mascota mascota = new Mascota(chip,nombre,tipo,raza,dniCliente);
                mascotas.add(mascota);
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            alertas.crearAlerta("Error",e.toString(), Alert.AlertType.ERROR);
        }

        return mascotas;
    }

    @Override
    public void actualizar(Mascota entity, String chip) {
        String query = "UPDATE Mascotas " +
                "SET masc_chip = " + "'" + entity.getChip() + "'" + "," +
                "masc_nombre = " + "'" + entity.getNombre() + "'" + "," +
                "masc_tipo =  " + "'" + entity.getTipo() + "'" + "," +
                "masc_raza = " + "'" + entity.getRaza() + "'" + "," +
                "cli_dni = " + "'" + entity.getDniCliente() + "'" + " where masc_chip = " +  "'" + chip + "'";
        Conexion con = new Conexion();
        con.ejecutarActualizacion(query);
        con.close();
    }

    @Override
    public void borrar(String id) {
        String query = "DELETE FROM Mascotas where masc_chip = '" + id + "'";
        Conexion con = new Conexion();
        con.ejecutarActualizacion(query);
        con.close();
    }


}
