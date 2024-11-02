package com.megalabsapi.model.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EncuestaRecojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCliente;
    private String idPedido;

    private int puntualidadRecojo;
    private int estadoProducto;
    private int amabilidadPersonal;
    private int claridadInstrucciones;

    private String comentarios;
    private LocalDateTime fechaEncuesta;

    public EncuestaRecojo() {
        this.fechaEncuesta = LocalDateTime.now();
    }
}