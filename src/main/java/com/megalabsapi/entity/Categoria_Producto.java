package com.megalabsapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria_Producto")
public class Categoria_Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Categoria")
    private Integer idCategoria;

    @Column(name = "Nombre_Categoria", nullable = false, length = 20)
    private String nombreCategoria;

    @ManyToOne
    @JoinColumn(name = "Producto_ID_Producto", referencedColumnName = "ID_Producto", foreignKey = @ForeignKey(name = "fk_categoria_producto_producto"))
    private Producto producto;
}