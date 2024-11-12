package com.megalabsapi.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ProgramacionRecojoDTO {
    private LocalDate fechaRecojo;
    private String nombreMuestra;
    private Integer cantidadMuestra;
    private Boolean confirmado;
    private String clienteRuc; // RUC del cliente
    private String nombreCliente; // Nombre del cliente (opcional para mostrar en respuestas)
}

