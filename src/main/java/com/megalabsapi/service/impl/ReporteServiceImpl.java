package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Reporte;
import com.megalabsapi.service.ReporteService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class ReporteServiceImpl<T extends Reporte> implements ReporteService<T> {

    private final JpaRepository<T, Integer> repository;

    public ReporteServiceImpl(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    protected JpaRepository<T, Integer> getRepository() {
        return repository;
    }

    @Override
    public T crearReporte(T reporte) {
        return repository.save(reporte);
    }

    @Override
    public T enviarReporte(Integer id) {
        T reporte = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
        reporte.setEnviado(true);
        return repository.save(reporte);
    }

    @Override
    public List<T> obtenerReportesEnviados() {
        return repository.findAll().stream().filter(Reporte::isEnviado).toList();
    }

    @Override
    public List<T> obtenerTodos() {
        return repository.findAll();
    }
}
