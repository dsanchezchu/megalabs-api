package com.megalabsapi.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "Entrevista")
public class Entrevista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Entrevista")
    private Integer idEntrevista;

    @Column(name = "Fecha", nullable = false)
    private Date fecha;

    @Column(name = "Hora", nullable = false)
    private Time hora;

    @Column(name = "Lugar_Sede", nullable = false, length = 50)
    private String lugarSede;

    @ManyToOne
    @JoinColumn(name = "Representante_DNI", referencedColumnName = "DNI", foreignKey = @ForeignKey(name = "fk_entrevista_representante"))
    private Representante representante;

    @ManyToOne
    @JoinColumn(name = "Cliente_RUC", referencedColumnName = "RUC", foreignKey = @ForeignKey(name = "fk_entrevista_cliente"))
    private Cliente cliente;
}
