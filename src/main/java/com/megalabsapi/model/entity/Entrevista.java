package com.megalabsapi.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@Table(name = "entrevista")
@Entity
public class Entrevista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrevista;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "hora", nullable = false)
    private Time hora;

    @Column(name = "lugar_sede", nullable = false, length = 50)
    private String lugarSede;

    @ManyToOne
    @JoinColumn(name = "representante_dni", referencedColumnName = "dni", nullable = false, foreignKey = @ForeignKey(name = "fk_entrevista_representante"))
    private Representante representante;

    @ManyToOne
    @JoinColumn(name = "cliente_ruc", referencedColumnName = "ruc", nullable = false, foreignKey = @ForeignKey(name = "fk_entrevista_cliente"))
    private Cliente cliente;
}
