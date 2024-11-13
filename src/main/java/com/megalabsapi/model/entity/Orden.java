package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.EstadoOrden;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoOrden estado; // Enum: ENTREGADO, PENDIENTE

    @Column(name = "problema", length = 500)
    private String problema; // Detalles de cualquier problema con la orden

    @ManyToOne
    @JoinColumn(name = "Cliente_RUC", referencedColumnName = "RUC", nullable = false)
    private Cliente cliente; // Asociación con la entidad Cliente

    @ManyToOne
    @JoinColumn(name = "ID_Detalle_Venta", referencedColumnName = "ID_Detalle_Venta", nullable = true)
    private Detalle_Venta detalleVenta; // Asociación opcional con la entidad Detalle_Venta

    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion = new Date(); // Fecha de última actualización
}
