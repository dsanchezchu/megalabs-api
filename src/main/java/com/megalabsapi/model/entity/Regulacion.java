package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.AuditoriaStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

@Data
@Entity
@Table(name = "regulacion")
public class Regulacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Regulacion")
    private Integer idRegulacion;

    @Column(name = "Nombre_Regulacion", nullable = false, length = 50)
    private String nombreRegulacion;

    @Column(name = "Fecha_Auditoria", nullable = false)
    private Date fechaAuditoria;

    @Column(name = "Estado_Auditoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuditoriaStatus estadoAuditoria;

    @ManyToOne
    @JoinColumn(name = "Producto_ID_Producto", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;
}
