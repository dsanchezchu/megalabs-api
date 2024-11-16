package com.megalabsapi.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "no_conformidad")
public class NoConformidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNoConformidad;

    @Column(name = "descripcion_problema", nullable = false, length = 500)
    private String descripcionProblema;

    @Column(name = "fecha_deteccion", nullable = false)
    private Date fechaDeteccion;

    @Column(name = "causa_raiz", length = 500)
    private String causaRaiz;

    @Column(name = "acciones_correctivas", length = 500)
    private String accionesCorrectivas;

    @Column(name = "resultado_seguimiento", length = 500)
    private String resultadoSeguimiento;

    @Column(name = "critico", nullable = false)
    private Boolean critico;

    @ManyToOne
    @JoinColumn(name = "entrega_muestra_id", referencedColumnName = "ID_Entrega", nullable = false)
    private Entrega_Muestra entregaMuestra;
}