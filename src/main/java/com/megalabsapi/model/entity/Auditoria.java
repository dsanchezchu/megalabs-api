package com.megalabsapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "accion", nullable = false, length = 100)
    private String accion;

    @Column(name = "pantalla", nullable = false, length = 50)
    private String pantalla;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;
}

