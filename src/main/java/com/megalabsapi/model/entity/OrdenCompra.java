package com.megalabsapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreMateriaPrima;
    private Integer cantidadSolicitada;
    private Double costoTotal;
    private Integer tiempoEstimadoEntrega;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

}