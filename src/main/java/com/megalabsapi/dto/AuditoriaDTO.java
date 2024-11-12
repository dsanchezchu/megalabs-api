package com.megalabsapi.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class AuditoriaDTO {

    private Long id;
    private LocalDateTime fechaHora;
    private String accion;
    private String pantalla;
    private String usuario;

}
