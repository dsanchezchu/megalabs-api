package com.megalabsapi.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Venta")
    private Integer idVenta;

    @Column(name = "Fecha", nullable = false)
    private Date fecha;

    @Column(name = "Hora", nullable = false)
    private Time hora;

    @ManyToOne
    @JoinColumn(name = "Pago_Id_Pago", referencedColumnName = "Id_Pago", foreignKey = @ForeignKey(name= "fk_venta_pago"), nullable = false)
    private Pago pago;
}
