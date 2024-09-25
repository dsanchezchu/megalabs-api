package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.PagoStatus;
import com.megalabsapi.model.enums.PaymentMethod;
import lombok.Data;
import jakarta.persistence.*;
import java.sql.Date;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Data
@Entity
@Table(name = "Pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Pago")
    private Integer idPago;

    @Column(name = "Monto_Total", nullable = false)
    private Double montoTotal;

    @Column(name = "Fecha_Pago", nullable = false)
    private Date fechaPago;

    @Column(name = "Metodo_Pago", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PaymentMethod metodoPago;

    @Column(name = "Estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PagoStatus estado;
}