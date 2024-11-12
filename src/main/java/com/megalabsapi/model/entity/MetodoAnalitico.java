package com.megalabsapi.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "metodo_analitico")
public class MetodoAnalitico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMetodo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "precision", nullable = false, length = 50)
    private String precision;

    @Column(name = "fiabilidad", nullable = false, length = 50)
    private String fiabilidad;

    @Column(name = "fecha_validacion", nullable = false)
    private Date fechaValidacion;

    @Column(name = "documentacion_url", nullable = false, length = 255)
    private String documentacionUrl;  // URL de la documentación de validación
}

