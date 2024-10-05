package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.ControlCalidadStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

@Data
@Entity
@Table(name = "control_Calidad")
public class Control_Calidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Control")
    private Integer idControl;

    @Column(name = "Fecha", nullable = false)
    private Date fecha;

    @Column(name = "Resultado", nullable = false, length = 50)
    private String resultado;

    @Column(name = "Estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private ControlCalidadStatus estado;

    @ManyToOne
    @JoinColumn(name = "Producto_ID_Producto", referencedColumnName = "ID_Producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "cliente_ruc", referencedColumnName = "RUC", nullable = false)
    private Cliente cliente;
}
