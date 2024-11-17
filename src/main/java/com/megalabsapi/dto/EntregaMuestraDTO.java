package com.megalabsapi.dto;

import com.megalabsapi.model.enums.EntregaStatus;
import java.sql.Date;
import java.time.LocalDateTime;


public class EntregaMuestraDTO {
    private Integer idEntrega;
    private String lugar;
    private Date fecha;
    private EntregaStatus estado; // Ahora usando el enum directamente
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor
    public EntregaMuestraDTO(Integer idEntrega, String lugar, Date fecha, EntregaStatus estado, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idEntrega = idEntrega;
        this.lugar = lugar;
        this.fecha = fecha;
        this.estado = estado;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters y Setters (ya los conoces)

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

    public EntregaStatus getEstado() {
        return estado;
    }

    public void setEstado(EntregaStatus estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
