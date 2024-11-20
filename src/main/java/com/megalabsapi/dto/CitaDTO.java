package com.megalabsapi.dto;

import com.megalabsapi.model.enums.CitaMotivo;
import com.megalabsapi.model.enums.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CitaDTO {
    private Long id;
    private String clienteNombre;
    private String clienteRuc;
    private String representanteNombre;
    private String representanteDni;
    private CitaMotivo motivo;
    private LocalDateTime fechaHora;
    private EstadoCita estado;
    private boolean cancelada;
}
