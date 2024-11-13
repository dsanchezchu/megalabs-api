package com.megalabsapi.model.entity;

import java.util.Date;

import com.megalabsapi.model.enums.EstadoReporte;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion = new Date();

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reporte", nullable = false)
    private EstadoReporte estadoReporte; // Enum que define el estado (APROBADO, NO_APROBADO)

    @Column(name = "enviado", nullable = false)
    private boolean enviado = false;

    // Métodos comunes
    public void enviarReporte() {
        this.enviado = true;
    }
}
