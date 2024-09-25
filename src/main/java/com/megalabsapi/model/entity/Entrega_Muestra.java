package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.EntregaStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

@Data
@Entity
@Table(name = "Entrega_Muestra")
public class Entrega_Muestra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Entrega")
    private Integer idEntrega;

    @Column(name = "Fecha", nullable = false)
    private Date fecha;

    @Column(name = "Lugar", nullable = false, length = 50)
    private String lugar;

    @Column(name = "Estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntregaStatus estado; // Usando el enum

    @ManyToOne
    @JoinColumn(name = "Cliente_RUC", referencedColumnName = "RUC", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Producto_ID_Producto", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;
}
