package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.EncuestaRecojo;
import com.megalabsapi.repository.EncuestaRecojoRepository;
import com.megalabsapi.service.EncuestaRecojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncuestaRecojoServiceImpl implements EncuestaRecojoService {
    @Autowired
    private EncuestaRecojoRepository encuestaRecojoRepository;

    @Override
    public EncuestaRecojo guardarEncuesta(EncuestaRecojo encuesta) {
        return encuestaRecojoRepository.save(encuesta);
    }

    @Override
    public List<EncuestaRecojo> obtenerTodasEncuestas() {
        return encuestaRecojoRepository.findAll();
    }

    @Override
    public double calcularPromedio(String campo) {
        List<EncuestaRecojo> encuestas = encuestaRecojoRepository.findAll();
        return encuestas.stream()
                .mapToInt(encuesta -> {
                    switch (campo) {
                        case "puntualidadRecojo":
                            return encuesta.getPuntualidadRecojo();
                        case "estadoProducto":
                            return encuesta.getEstadoProducto();
                        case "amabilidadPersonal":
                            return encuesta.getAmabilidadPersonal();
                        case "claridadInstrucciones":
                            return encuesta.getClaridadInstrucciones();
                        default:
                            return 0;
                    }
                })
                .average()
                .orElse(0.0);
    }
}
