package com.megalabsapi.entity;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "representante")
public class Representante {

    @Id
    @Column(name = "dni", length = 8)
    private String dni;

    @Column(name = "nombre", nullable = false, length = 50)  // Ajuste de longitud para nombres completos
    private String nombre;

    @Column(name = "sede_asignada", length = 50)
    private String sedeAsignada;

    @ManyToOne
    @JoinColumn(name = "laboratorio_ruc", referencedColumnName = "RUC", foreignKey = @ForeignKey(name = "fk_representante_laboratorio"))
    private Laboratorio laboratorio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;  // Relación con la entidad Role

    @Column(name = "contraseña", nullable = false)
    private String contraseña;  // Asegúrate de encriptar este valor antes de guardar

    @Column(name = "intentos", nullable = false)
    private int intentosFallidos;

    @Column(name = "email", nullable = false, length = 100)  // Ajuste de longitud para correos electrónicos largos
    private String email;
}
