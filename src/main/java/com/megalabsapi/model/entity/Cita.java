package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.CitaMotivo;
import com.megalabsapi.model.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_ruc", nullable = false) // Relación con Cliente
    private Cliente cliente;

    @Enumerated(EnumType.STRING) // Guardar motivo como texto
    private CitaMotivo motivo;

    private LocalDateTime fechaHora;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private boolean cancelada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "representante_dni", nullable = false) // Relación con Representante
    private Representante representante;
}