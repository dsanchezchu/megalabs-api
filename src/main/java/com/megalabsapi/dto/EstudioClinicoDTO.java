package com.megalabsapi.dto;

import java.sql.Date;

public class EstudioClinicoDTO {
    private Integer idControl;
    private String nombreProducto;
    private String nombreMedico;
    private Date fechaAuditoria;
    private String resultado;

    // Constructor
    public EstudioClinicoDTO(Integer idControl, String nombreProducto, String nombreMedico, Date fechaAuditoria, String resultado) {
        this.idControl = idControl;
        this.nombreProducto = nombreProducto;
        this.nombreMedico = nombreMedico;
        this.fechaAuditoria = fechaAuditoria;
        this.resultado = resultado;
    }

    public Integer getIdControl() {
        return idControl;
    }

    public void setIdControl(Integer idControl) {
        this.idControl = idControl;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public Date getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(Date fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}

