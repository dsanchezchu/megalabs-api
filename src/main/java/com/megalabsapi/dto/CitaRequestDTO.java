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
public class CitaRequestDTO {
    private String nombreCliente;
    private CitaMotivo motivo;
    private LocalDateTime fechaHora;
    private String dniRepresentante;
    private EstadoCita estado; // Asegúrate de agregar esto
}
