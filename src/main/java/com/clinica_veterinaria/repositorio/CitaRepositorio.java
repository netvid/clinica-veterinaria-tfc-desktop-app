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
        String query = "INSERT INTO " + tableName + " (cita_fecha, cli_dni, vet_dni, masc_chip) values (" +
                "'" + entity.getFecha() + "'"  + "," +
                "'" + entity.getDniCliente() + "'" + "," +
                "'" + entity.getDniVeterinario() + "'" + "," +
                "'" + entity.getChipMascota() + "'" + ");";
        System.out.println(query);

        Conexion con = new Conexion();
        con.ejecutarActualizacion(query);
        con.close();
    }

    @Override
    public ObservableList<Cita> obtenerTodos(String busqueda) {
        ObservableList<Cita> citas = FXCollections.observableArrayList();
        Conexion con = new Conexion();

        try{
            String query = !busqueda.equals("") ? String.format("SELECT * FROM %s WHERE %s = %d",tableName,pkName,Integer.valueOf(busqueda)) : "SELECT * FROM " + tableName;
            ResultSet rs = con.ejecutarConsulta(query);
            while(rs.next()){
                Long id = rs.getLong("cita_id");
                String fecha = rs.getString("cita_fecha");
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
        }catch(NumberFormatException e){
            this.alertas.crearAlerta("Error en formato","El valor introducido debe ser un numero",Alert.AlertType.ERROR);
        }

        return citas;
    }

    @Override
    public void actualizar(Cita entity, Long id) {
        String query = String.format("UPDATE %s SET cli_dni = '%s' , vet_dni = '%s', masc_chip = '%s' , cita_fecha = '%s' ",tableName,entity.getDniCliente(),entity.getDniVeterinario(),entity.getChipMascota(),entity.getFecha());
        Conexion con = new Conexion();
        con.ejecutarActualizacion(query);
        con.close();
    }

    @Override
    public void borrar(Long id) {
        String query = "DELETE FROM " + tableName + " where cita_id = " + id;
        Conexion con = new Conexion();
        con.ejecutarActualizacion(query);
        con.close();
    }
}
