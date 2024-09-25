package com.megalabsapi.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Detalle_Venta")
public class Detalle_Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Detalle_Venta")
    private Integer idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "ID_Venta", referencedColumnName = "ID_Venta", foreignKey = @ForeignKey(name = "fk_detalle_venta_venta"))
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "Producto_ID_Producto", referencedColumnName = "ID_Producto", foreignKey = @ForeignKey(name = "fk_detalle_venta_producto"))
    private Producto producto;

    @Column(name = "Cliente_RUC", length = 11)
    private String clienteRUC;

    @Column(name = "Cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "Precio", nullable = false)
    private BigDecimal precio;
}