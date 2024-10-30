package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.EntregaStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "entrega_Muestra")
public class Entrega_Muestra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Entrega")
    private Integer id;

    @Column(name = "Fecha", nullable = false)
    private Date fecha;

    @Column(name = "Lugar", nullable = false, length = 50)
    private String lugar;

    @Column(name = "Estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntregaStatus estado; // Usando el enum

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "Cliente_RUC", referencedColumnName = "RUC", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Producto_ID_Producto", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;

}
