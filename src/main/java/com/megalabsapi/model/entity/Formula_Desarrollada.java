package com.megalabsapi.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "formula_desarrollada")
public class Formula_Desarrollada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFormula;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "beneficios", nullable = false, length = 500)
    private String beneficios;

    @Column(name = "ingredientes_clave", nullable = false)
    private String ingredientesClave;

    @Column(name = "diferencias", length = 500)
    private String diferencias;

    @Column(name = "fecha_desarrollo", nullable = false)
    private Date fechaDesarrollo;

    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;
}

