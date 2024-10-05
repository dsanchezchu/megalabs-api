package com.megalabsapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "estudio_clinico")
public class EstudioClinico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudio")
    private Long idEstudio;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private String estado; // Ej. "EN_PROGRESO", "FINALIZADO"
}