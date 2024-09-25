package com.megalabsapi.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@Entity
@Table(name = "Venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Venta")
    private Integer idVenta;

    @Column(name = "Fecha", nullable = false)
    private Date fecha;

    @Column(name = "Hora", nullable = false)
    private Time hora;

    @OneToMany(mappedBy = "venta")
    private List<Detalle_Venta> detalles;

    @ManyToOne
    @JoinColumn(name = "Pago_Id_Pago", referencedColumnName = "Id_Pago", foreignKey = @ForeignKey(name = "fk_venta_pago"))
    private Pago pago;
}
