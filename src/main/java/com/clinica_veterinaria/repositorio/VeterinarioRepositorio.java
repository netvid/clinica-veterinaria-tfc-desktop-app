package com.clinica_veterinaria.repositorio;

import com.clinica_veterinaria.conexion.Conexion;
import com.clinica_veterinaria.modelo.Veterinario;
import com.clinica_veterinaria.utiles.MisAlertas;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VeterinarioRepositorio {
    private MisAlertas alertas = new MisAlertas();

    public ObservableList<Veterinario> getObservableListOfVeterinarios(String busqueda){
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
            alertas.crearAlerta("Error",e.toString(),"error");
        }

        return veterinarios;
    }

    public void insertarVeterinario(Veterinario veterinario){
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

    public void eliminarVeterinario(Veterinario veterinario){
        String query = "DELETE FROM VETERINARIOS where vet_dni = '" + veterinario.getDni() + "';";
        Conexion con = new Conexion();

        con.ejecutarActualizacion(query);
        con.close();
    }

    public void actualizarVeterinario(Veterinario veterinario){
        String query = "UPDATE Veterinarios " +
                "SET vet_dni = " + "'" + veterinario.getDni() + "'" + "," +
                "vet_nombre = " + "'" + veterinario.getNombre() + "'" + "," +
                "vet_apellidos =  " + "'" + veterinario.getApellidos() + "'" + "," +
                "vet_fecha_nac = " + "'" + veterinario.getFechaNac() + "'" + "," +
                "vet_sector = " + "'" + veterinario.getSector() + "'" + " where vet_dni = " +  "'" + veterinario.getDni() + "'";

        Conexion con = new Conexion();

        con.ejecutarActualizacion(query);
        con.close();
    }
}
