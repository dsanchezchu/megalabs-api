package com.megalabsapi.dto;

import java.sql.Date;
import java.sql.Time;

public class EntrevistaDTO {
    private Integer idEntrevista;
    private Date fecha;
    private Time hora;
    private String lugarSede;
    private String nombreCliente;  // Información del cliente (médico)
    private String nombreRepresentante;

    // Constructor
    public EntrevistaDTO(Integer idEntrevista, Date fecha, Time hora, String lugarSede, String nombreCliente, String nombreRepresentante) {
        this.idEntrevista = idEntrevista;
        this.fecha = fecha;
        this.hora = hora;
        this.lugarSede = lugarSede;
        this.nombreCliente = nombreCliente;
        this.nombreRepresentante = nombreRepresentante;
    }

    // Getters y Setters
    public Integer getIdEntrevista() {
        return idEntrevista;
    }

    public void setIdEntrevista(Integer idEntrevista) {
        this.idEntrevista = idEntrevista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getLugarSede() {
        return lugarSede;
    }

    public void setLugarSede(String lugarSede) {
        this.lugarSede = lugarSede;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }
}
