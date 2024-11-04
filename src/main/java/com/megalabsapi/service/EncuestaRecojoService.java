package com.megalabsapi.service;

import com.megalabsapi.model.entity.EncuestaRecojo;
import java.util.List;

public interface EncuestaRecojoService {
    EncuestaRecojo guardarEncuesta(EncuestaRecojo encuesta);
    List<EncuestaRecojo> obtenerTodasEncuestas();
    double calcularPromedio(String campo);
}
