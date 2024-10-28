package com.megalabsapi.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "representante")
public class Representante {
    @Id
    @Column(name = "DNI", length = 8)
    private String dni;

    @Column(name = "Nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "Sede_Asignada", length = 50)
    private String sedeAsignada;

    @ManyToOne
    @JoinColumn(name = "Laboratorio_RUC", referencedColumnName = "RUC", foreignKey = @ForeignKey(name = "fk_representante_laboratorio"))
    private Laboratorio laboratorio;

    @Column(name = "Contraseña", nullable = false)
    private String contraseña;

    @Column(name = "intentos", nullable = false)
    private int intentosFallidos;

    @Column(name = "email", nullable = false)
    private String email;

}