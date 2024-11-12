package com.megalabsapi.model.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EncuestaEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCliente;
    private String idPedido;

    private int puntualidadEntrega;
    private int estadoProducto;
    private int profesionalismoPersonal;
    private int facilidadContacto;

    private String comentarios;
    private LocalDateTime fechaEncuesta;

    public EncuestaEntrega() {
        this.fechaEncuesta = LocalDateTime.now();
    }

}