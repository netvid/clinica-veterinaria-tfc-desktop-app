package com.clinica_veterinaria.repositorio;

import com.clinica_veterinaria.conexion.Conexion;
import com.clinica_veterinaria.interfaces.IDao;
import com.clinica_veterinaria.modelo.Veterinario;
import com.clinica_veterinaria.utiles.MisAlertas;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioRepositorio implements IDao<Veterinario,String> {
    private MisAlertas alertas = new MisAlertas();

    public ObservableList<Veterinario> obtenerTodos(String busqueda){
        ObservableList<Veterinario> veterinarios = FXCollections.observableArrayList();
        Conexion con = new Conexion();
        String query = "";

        if(!busqueda.equals("")) query = "SELECT * FROM Veterinarios where vet_dni like '%" + busqueda + "%'";
        else query = "SELECT * FROM Veterinarios";

        try {
            ResultSet rs = con.ejecutarConsulta(query);

            while(rs.next()){
                String dni = rs.getString("vet_dni");
                String nombre = rs.getString("vet_nombre");
                String apellidos = rs.getString("vet_apellidos");
                String sector = rs.getString("vet_sector");
                Date fechaNac = rs.getDate("vet_fecha_nac");

                Veterinario veterinario = new Veterinario(dni,nombre,apellidos,sector,fechaNac);
                veterinarios.add(veterinario);
            }

            con.close();
            rs.close();

        } catch (SQLException e) {
            alertas.crearAlerta("Error",e.toString(), Alert.AlertType.ERROR);
        }

        return veterinarios;
    }

    public void create(Veterinario veterinario){
        String query = "INSERT INTO VETERINARIOS values(" +
                "'" + veterinario.getDni() + "'"  + ","  +
                "'" + veterinario.getNombre() + "'" + "," +
                "'" + veterinario.getApellidos() + "'" + "," +
                "'" + veterinario.getFechaNac() + "'" + "," +
                "'" + veterinario.getSector() + "'" + ");";

        Conexion con = new Conexion();

        con.ejecutarActualizacion(query);
        con.close();

    }
    public List<String> obtenerDniTodos(){
        List<String> dnis = new ArrayList<>();

        String query = "SELECT vet_dni from Veterinarios";
        Conexion con = new Conexion();
        try{
            ResultSet rs = con.ejecutarConsulta(query);
            while(rs.next()){
                String dni = rs.getString("vet_dni");
                dnis.add(dni);
            }
            rs.close();
            con.close();
        } catch (SQLException e) {
            alertas.crearAlerta("Error",e.toString(), Alert.AlertType.ERROR);
        }
        return dnis;
    }

    public void borrar(String dni){
        String query = "DELETE FROM VETERINARIOS where vet_dni = '" + dni + "';";
        Conexion con = new Conexion();

        con.ejecutarActualizacion(query);
        con.close();
    }

    public void actualizar(Veterinario veterinario,String dni){
        String query = "UPDATE Veterinarios " +
                "SET vet_dni = " + "'" + veterinario.getDni() + "'" + "," +
                "vet_nombre = " + "'" + veterinario.getNombre() + "'" + "," +
                "vet_apellidos =  " + "'" + veterinario.getApellidos() + "'" + "," +
                "vet_fecha_nac = " + "'" + veterinario.getFechaNac() + "'" + "," +
                "vet_sector = " + "'" + veterinario.getSector() + "'" + " where vet_dni = " +  "'" + dni + "'";

        Conexion con = new Conexion();

        con.ejecutarActualizacion(query);
        con.close();
    }
}
