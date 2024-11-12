package com.megalabsapi.dto;

import java.sql.Date;

public class EntregaMuestraDTO {
    private Integer idEntrega;
    private String lugar;
    private Date fecha;
    private String estado;  // Puedes usar un Enum, pero aquí es un String para simplificar

    // Constructor
    public EntregaMuestraDTO(Integer idEntrega, String lugar, Date fecha, String estado) {
        this.idEntrega = idEntrega;
        this.lugar = lugar;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters y Setters
    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Integer idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

