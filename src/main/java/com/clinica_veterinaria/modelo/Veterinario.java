package com.clinica_veterinaria.modelo;

import java.sql.Date;

public class Veterinario {
    private String dni;

    private String nombre;

    private String apellidos;

    private String sector;

    private Date fechaNac;

    public Veterinario(String dni, String nombre, String apellidos,String sector, Date fechaNac){
        this.dni = dni;
        this.nombre =  nombre;
        this.apellidos = apellidos;
        this.sector = sector;
        this.fechaNac = fechaNac;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Date getFechaNac(){
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
}

