package com.megalabsapi.dto;

import com.megalabsapi.model.enums.ControlCalidadStatus;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

@Data
public class RegistroEstudioClinicoDTO {

    @NotNull
    private int productoId;

    @NotNull
    private LocalDate fecha; // Cambio de java.sql.Date a LocalDate

    @NotNull
    private String resultado;

    @NotNull
    private List<Integer> metodosAnaliticosIds;

    private ControlCalidadStatus estado = ControlCalidadStatus.EN_PRUEBAS; // Valor por defecto si es null

    @NotNull
    private String clienteRuc;
}
