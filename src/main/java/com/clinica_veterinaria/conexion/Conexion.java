package com.clinica_veterinaria.conexion;

import com.clinica_veterinaria.utiles.MisAlertas;

import java.sql.*;

public class Conexion {
    private String host = "localhost";
    private String port = "5432";
    private String user = "postgres";
    private String password = "toor";
    private String db = "clinica-veterinaria-tfg";

    private String url = "jdbc:postgresql://" + host + ":" + port + "/" + db + "?user=" + user + "&password="  + password;
    private Connection con;

    private MisAlertas alertas = new MisAlertas();

    public Conexion(){
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(true);
            System.out.println("Conexion realizada con exito");
        } catch (SQLException e) {
            alertas.crearAlerta("Error",e.toString(),"error");
        }
    }

    /**
     * Devuelve un objeto ResultSet con los datos provenientes de una consulta.
     * @param query La consulta que se ejecutara en la base de datos.
     * @return
     * @throws SQLException
     */
    public ResultSet ejecutarConsulta(String query) {
        Statement statement  = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            alertas.crearAlerta("Error",e.toString(),"error");
        }

        return rs;
    }

    public boolean ejecutarActualizacion(String query) {
        Statement statement  = null;
        boolean result = false;

        try {
            statement = con.createStatement();
            result = statement.executeUpdate(query) > 0;
        } catch (SQLException e) {
            alertas.crearAlerta("Error",e.toString(),"error");
        }

        return result;
    }

    public void close(){
        try {
            this.con.close();
        } catch (SQLException e) {
            alertas.crearAlerta("Error",e.toString(),"error");
        }
    }
}
