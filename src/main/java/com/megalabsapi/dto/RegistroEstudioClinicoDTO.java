package com.megalabsapi.dto;

import lombok.Data;
import java.sql.Date;
import java.util.List;

@Data
public class RegistroEstudioClinicoDTO {
    private Integer productoId; // ID del medicamento
    private Date fecha;
    private String resultado;
    private List<Integer> metodosAnaliticosIds; // IDs de los métodos analíticos
    private String documentacion; // Documentación adicional
}