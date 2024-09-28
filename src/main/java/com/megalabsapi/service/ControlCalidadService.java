package com.megalabsapi.service;

import com.megalabsapi.model.entity.Control_Calidad;
import java.sql.Date;
import java.util.List;

public interface ControlCalidadService {
    List<Control_Calidad> buscarPorProducto(Integer idProducto);
    Control_Calidad guardarControlCalidad(Control_Calidad controlCalidad);
    Control_Calidad actualizarControlCalidad(Integer idControl, Control_Calidad controlCalidad);
    void eliminarControlCalidad(Integer idControl);
    List<Control_Calidad> listarControlesCalidad();
}