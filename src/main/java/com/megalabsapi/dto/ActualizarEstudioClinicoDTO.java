package com.megalabsapi.dto;

import com.megalabsapi.model.enums.ControlCalidadStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ActualizarEstudioClinicoDTO {

    @NotNull
    private int idControl; // El ID del estudio clínico a actualizar

    private Integer productoId; // El ID del producto, puede ser null si no se actualiza

    private LocalDate fecha; // La nueva fecha, si se actualiza

    private String resultado; // El nuevo resultado, si se actualiza

    private List<Integer> metodosAnaliticosIds; // Lista de IDs de métodos analíticos

    private String clienteRuc; // El RUC del cliente, si se actualiza

    private ControlCalidadStatus estado; // El nuevo estado, si se actualiza
}
