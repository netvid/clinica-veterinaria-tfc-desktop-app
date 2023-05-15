package com.clinica_veterinaria.modelo;

import java.util.Date;

public class Cita {
    private Long id;
    private Date fecha;
    private String dniCliente;
    private String dniVeterinario;
    private String chipMascota;

    public Cita(Long id, Date fecha, String dniCliente, String dniVeterinario, String  chipMascota){
        this.id = id;
        this.fecha = fecha;
        this.dniCliente = dniCliente;
        this.dniVeterinario = dniVeterinario;
        this.chipMascota = chipMascota;
    }

    public Cita(){

    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getDniVeterinario() {
        return dniVeterinario;
    }

    public void setDniVeterinario(String dniVeterinario) {
        this.dniVeterinario = dniVeterinario;
    }

    public String getChipMascota() {
        return chipMascota;
    }

    public void setChipMascota(String chipMascota) {
        this.chipMascota = chipMascota;
    }
}
