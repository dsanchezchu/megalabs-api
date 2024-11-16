package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.EncuestaEntrega;
import com.megalabsapi.repository.EncuestaEntregaRepository;
import com.megalabsapi.service.EncuestaEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncuestaEntregaServiceImpl implements EncuestaEntregaService {
    @Autowired
    private EncuestaEntregaRepository encuestaRepository;

    @Override
    public EncuestaEntrega guardarEncuesta(EncuestaEntrega encuesta) {
        return encuestaRepository.save(encuesta);
    }

    @Override
    public List<EncuestaEntrega> obtenerTodasEncuestas() {
        return encuestaRepository.findAll();
    }

    @Override
    public double calcularPromedio(String campo) {
        List<EncuestaEntrega> encuestas = encuestaRepository.findAll();
        return encuestas.stream()
                .mapToInt(encuesta -> {
                    switch (campo) {
                        case "puntualidadEntrega":
                            return encuesta.getPuntualidadEntrega();
                        case "estadoProducto":
                            return encuesta.getEstadoProducto();
                        case "profesionalismoPersonal":
                            return encuesta.getProfesionalismoPersonal();
                        case "facilidadContacto":
                            return encuesta.getFacilidadContacto();
                        default:
                            return 0;
                    }
                })
                .average()
                .orElse(0.0);
    }
}
