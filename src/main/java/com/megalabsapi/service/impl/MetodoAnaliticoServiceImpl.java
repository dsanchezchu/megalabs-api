package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Control_Calidad;
import com.megalabsapi.model.entity.MetodoAnalitico;
import com.megalabsapi.repository.MetodoAnaliticoRepository;
import com.megalabsapi.service.MetodoAnaliticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MetodoAnaliticoServiceImpl implements MetodoAnaliticoService {

    @Autowired
    private MetodoAnaliticoRepository metodoAnaliticoRepository;

    @Override
    public List<MetodoAnalitico> obtenerTodosMetodos() {
        return metodoAnaliticoRepository.findAll();
    }

    @Override
    public Optional<MetodoAnalitico> obtenerMetodoPorId(Integer id) {
        return metodoAnaliticoRepository.findById(id);
    }

    @Override
    public List<MetodoAnalitico> obtenerMetodosPorControlCalidad(Control_Calidad controlCalidad) {
        return controlCalidad.getMetodosAnaliticos();  // Obtiene métodos analíticos asociados a un control de calidad
    }

    @Override
    public MetodoAnalitico crearMetodoAnalitico(MetodoAnalitico metodoAnalitico) {
        return metodoAnaliticoRepository.save(metodoAnalitico);
    }

    @Override
    public String generarResumenMetodo(MetodoAnalitico metodoAnalitico) {
        return "Método Analítico: " + metodoAnalitico.getNombre() + "\n" +
                "Descripción: " + metodoAnalitico.getDescripcion() + "\n" +
                "Precisión: " + metodoAnalitico.getPrecision() + "\n" +
                "Fiabilidad: " + metodoAnalitico.getFiabilidad() + "\n" +
                "Documentación: " + metodoAnalitico.getDocumentacionUrl();
    }
}

