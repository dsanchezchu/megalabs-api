package com.megalabsapi.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

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

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Detalle_Venta> detalleVenta;

    @ManyToOne
    @JoinColumn(name = "Pago_Id_Pago", referencedColumnName = "Id_Pago", foreignKey = @ForeignKey(name= "fk_venta_pago"), nullable = false)
    private Pago pago;
}
