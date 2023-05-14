package com.clinica_veterinaria.modelo;

public class Mascota {
    private String chip;
    private String nombre;
    private String tipo;

    private String raza;
    private String dniCliente;

    public Mascota(String chip, String nombre, String tipo, String raza, String dniCliente){
        this.chip = chip;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.dniCliente = dniCliente;
    }

    public Mascota(){

    }


    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }
}
