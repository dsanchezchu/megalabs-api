package com.megalabsapi.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "catalogo_Precio")
public class Catalogo_Precio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Precio")
    private Integer idPrecio;

    @Column(name = "Precio", nullable = false)
    private Double precio;

    @Column(name = "Fecha_Efectiva", nullable = false)
    private Date fechaEfectiva;

    @ManyToOne
    @JoinColumn(name = "Producto_ID_Producto", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;
}
