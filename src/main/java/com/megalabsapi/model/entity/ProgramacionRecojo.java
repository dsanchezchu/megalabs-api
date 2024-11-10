package com.megalabsapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "programacion_recojo")
public class ProgramacionRecojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_recojo", nullable = false)
    private LocalDate fechaRecojo;

    @Column(name = "nombre_muestra", nullable = false)
    private String nombreMuestra;

    @Column(name = "cantidad_muestra", nullable = false)
    private Integer cantidadMuestra;

    @Column(name = "confirmado", nullable = false)
    private Boolean confirmado = false;

    // Relación con Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_ruc", referencedColumnName = "RUC", nullable = false)
    private Cliente cliente;
}
