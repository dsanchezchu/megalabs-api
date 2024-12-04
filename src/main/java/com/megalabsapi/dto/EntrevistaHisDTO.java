package com.megalabsapi.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class EntrevistaHisDTO {
    private Integer idEntrevista;
    private Date fecha;
    private Time hora;
    private String lugarSede;
    private String representanteDni;
    private String clienteRuc;
}
