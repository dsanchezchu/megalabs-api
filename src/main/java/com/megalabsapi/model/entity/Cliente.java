package com.megalabsapi.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "RUC", length = 11)
    private String ruc;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Licencia", length = 20)
    private String licencia;

    @Column(name = "Especializacion", nullable = false)
    private String especializacion;

    @Column(name = "Email", nullable = false)
    private String email; // Nuevo campo para almacenar el correo electrónico del cliente

    // Relación uno a muchos con ProgramacionRecojo
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProgramacionRecojo> programacionesRecojo;

    // Relación uno a muchos con Entrega_Muestra
    // @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<Entrega_Muestra> entregasMuestra;
}