package com.megalabsapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "reporte_cumplimiento_regulatorio")
public class ReporteCumplimientoRegulatorio extends Reporte {
    @Column(name = "contenido_auditoria", length = 500)
    private String contenidoAuditoria;

    @Column(name = "acciones_correctivas", length = 500)
    private String accionesCorrectivas;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;

}