package com.megalabsapi.model.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InsatisfaccionProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCliente;
    private String idPedido;

    @Enumerated(EnumType.STRING)
    private com.megalabsapi.model.enums.TipoQueja tipoQueja;

    private String comentarios;
    private String propuestaMejora;
    private boolean distribuido;

    public InsatisfaccionProducto() {
        this.distribuido = false;
    }
}

