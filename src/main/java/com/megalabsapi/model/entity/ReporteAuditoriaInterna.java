package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.ImpactoAuditoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "reporte_auditoria_interna")
public class ReporteAuditoriaInterna extends Reporte {
    @Enumerated(EnumType.STRING)
    @Column(name = "impacto", nullable = false)
    private ImpactoAuditoria impacto;

    @Column(name = "inconformidades", nullable = false, length = 500)
    private String inconformidades;

    @Column(name = "recomendaciones", nullable = true, length = 500)
    private String recomendaciones;

    // Getters y Setters
}
