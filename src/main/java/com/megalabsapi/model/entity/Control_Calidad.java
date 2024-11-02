package com.megalabsapi.model.entity;

import com.megalabsapi.model.enums.ControlCalidadStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.util.List;

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

    // Relación muchos a muchos con MetodoAnalitico
    @ManyToMany
    @JoinTable(
            name = "control_calidad_metodo_analitico",
            joinColumns = @JoinColumn(name = "control_calidad_id"),
            inverseJoinColumns = @JoinColumn(name = "metodo_analitico_id")
    )
    private List<MetodoAnalitico> metodosAnaliticos;
}
