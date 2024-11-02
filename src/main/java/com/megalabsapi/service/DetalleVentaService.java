package com.megalabsapi.service;

import com.megalabsapi.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DetalleVentaService {
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    public List<Object[]> obtenerReporteVentas() {
        return detalleVentaRepository.obtenerReporteVentas();
    }
}