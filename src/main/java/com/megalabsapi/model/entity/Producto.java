package com.megalabsapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.megalabsapi.model.enums.StockStatus;
import java.util.List;

@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Producto")
    private Integer idProducto;

    @Column(name = "Fecha_Venta")
    private java.sql.Date fechaVenta;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Stock", nullable = false)
    @Enumerated(EnumType.STRING)
    private StockStatus stock;

    // Relación con Detalle_Venta
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detalle_Venta> detalleVentas;

    // Relación con Entrega_Muestra
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Entrega_Muestra> entregaMuestras;

    // Relación con Control_Calidad
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Control_Calidad> controlCalidad;

    // Relación con Regulacion
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Regulacion> regulacione;

    // Relación con Catalogo_Precios
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Catalogo_Precio> catalogoPrecio;

    // Relación con Categoria_Producto
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categoria_Producto> categoriaProducto;


}