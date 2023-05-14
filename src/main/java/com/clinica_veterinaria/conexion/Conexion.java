package com.clinica_veterinaria.conexion;

import java.sql.*;

public class Conexion {
    private String host = "localhost";
    private String port = "5432";
    private String user = "postgres";
    private String password = "toor";
    private String db = "clinica-veterinaria-tfg";

    private String url = "jdbc:postgresql://" + host + ":" + port + "/" + db + "?user=" + user + "&password="  + password;
    private Connection con;

    public Conexion(){
        try {
            con = DriverManager.getConnection(url);
            con.setAutoCommit(true);
            System.out.println("Conexion realizada con exito");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Devuelve un objeto ResultSet con los datos provenientes de una consulta.
     * @param query La consulta que se ejecutara en la base de datos.
     * @return
     * @throws SQLException
     */
    public ResultSet ejecutarConsulta(String query) throws SQLException {
        Statement statement  = con.createStatement();
        return statement.executeQuery(query);
    }

    public boolean ejecutarActualizacion(String query) throws SQLException {
        Statement statement  = con.createStatement();
        boolean result = statement.executeUpdate(query) > 0;

        return result;
    }




    public void close(){
        try {
            this.con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
