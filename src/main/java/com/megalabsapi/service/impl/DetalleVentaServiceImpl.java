package com.megalabsapi.service.impl;

import com.megalabsapi.repository.DetalleVentaRepository;
import com.megalabsapi.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<Object[]> obtenerReporteVentas() {
        return detalleVentaRepository.obtenerReporteVentas();
    }
}
