package com.clinica_veterinaria.repositorio;

import com.clinica_veterinaria.conexion.Conexion;
import com.clinica_veterinaria.interfaces.IDao;
import com.clinica_veterinaria.modelo.Cliente;
import com.clinica_veterinaria.utiles.MisAlertas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRepositorio implements IDao<Cliente,String> {
    private String tableName = "clientes";
    private MisAlertas alertas = new MisAlertas();

    @Override
    public void create(Cliente entity) {
        String query = String.format("INSERT INTO %s values('%s', '%s', '%s', '%s'); ",tableName,entity.getDni(),entity.getNombre(),entity.getApellidos(),entity.getFechaNac());

        Conexion con = new Conexion();
        con.ejecutarActualizacion(query);
    }

    @Override
    public ObservableList<Cliente> obtenerTodos(String busqueda) {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        String query = !busqueda.equals("") ? "SELECT * FROM CLIENTES where cli_dni like " + "'%" + busqueda + "%'" : "SELECT * FROM CLIENTES";

        Conexion con = new Conexion();

        try{
            ResultSet rs = con.ejecutarConsulta(query);
            while(rs.next()){
                String dni = rs.getString("cli_dni");
                String nombre = rs.getString("cli_nombre");
                String apellidos = rs.getString("cli_apellidos");
                Date fechaNac = rs.getDate("cli_fecha_nac");

                Cliente cliente = new Cliente(dni,nombre,apellidos,fechaNac);
                clientes.add(cliente);
            }

            rs.close();
            con.close();
        } catch (SQLException e) {
            alertas.crearAlerta("Error",e.toString(), Alert.AlertType.ERROR);
        }

        return clientes;
    }

    @Override
    public void actualizar(Cliente entity) {
        String query = String.format("UPDATE %s SET cli_dni = '%s' , cli_nombre = '%s' , cli_apellidos = '%s' , cli_fecha_nac = '%s'",this.tableName,entity.getDni(),entity.getNombre(),entity.getFechaNac());
        Conexion con = new Conexion();
        con.ejecutarActualizacion(query);
        con.close();
    }

    @Override
    public void borrar(String id) {
        String query = "DELETE FROM CLIENTES where cli_dni = " + "'" + id + "'";
        Conexion con = new Conexion();
        con.ejecutarActualizacion(query);
        con.close();
    }
}
