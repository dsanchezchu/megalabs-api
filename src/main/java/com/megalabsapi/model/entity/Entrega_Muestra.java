package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.EntregaStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;

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
    private EntregaStatus estado;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "Cliente_RUC", referencedColumnName = "RUC", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Producto_ID_Producto", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;

    // Método para establecer las fechas automáticamente
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
