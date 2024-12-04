package com.megalabsapi.dto;

import com.megalabsapi.model.enums.ControlCalidadStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstudioClinicoDTO {
    private Integer idControl;
    private String nombreProducto;
    private String nombreMedico;
    private Date fechaAuditoria;
    private String resultado;
    private ControlCalidadStatus estado;
    private List<String> metodosAnaliticos;
}

