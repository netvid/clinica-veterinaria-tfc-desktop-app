package com.clinica_veterinaria.modelo;

import java.sql.Date;

public class Cliente {
    private String dni;
    private String nombre;
    private String apellidos;
    private Date fechaNac;


    public Cliente(String dni, String nombre, String apellidos, Date fechaNac){
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
    }

    public Cliente() {

    }

    public String getDni(){
        return this.dni;
    }

    public void setDni(String dni){
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
}
