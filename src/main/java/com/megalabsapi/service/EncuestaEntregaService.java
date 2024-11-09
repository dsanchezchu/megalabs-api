package com.megalabsapi.service;

import com.megalabsapi.model.entity.EncuestaEntrega;
import java.util.List;

public interface EncuestaEntregaService {
    EncuestaEntrega guardarEncuesta(EncuestaEntrega encuesta);
    List<EncuestaEntrega> obtenerTodasEncuestas();
    double calcularPromedio(String campo);
}
