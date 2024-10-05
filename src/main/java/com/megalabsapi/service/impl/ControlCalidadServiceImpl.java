package com.megalabsapi.service.impl;

import com.megalabsapi.dto.EstudioClinicoDTO;
import com.megalabsapi.model.entity.Control_Calidad;
import com.megalabsapi.repository.ControlCalidadRepository;
import com.megalabsapi.service.ControlCalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ControlCalidadServiceImpl implements ControlCalidadService {

    @Autowired
    private ControlCalidadRepository controlCalidadRepository;

    @Override
    public List<EstudioClinicoDTO> buscarPorProducto(String producto) {
        List<Control_Calidad> resultados = controlCalidadRepository.findByProducto(producto);

        // Convertir los resultados a DTOs
        return resultados.stream().map(control ->
                new EstudioClinicoDTO(
                        control.getIdControl(),
                        control.getProducto().getNombre(),
                        control.getCliente().getNombre(),  // Suponiendo que ahora haya una relación directa con Cliente
                        control.getFecha(),  // Utiliza 'fecha' de Control_Calidad
                        control.getResultado()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<EstudioClinicoDTO> buscarPorCliente(String cliente) {
        List<Control_Calidad> resultados = controlCalidadRepository.findByCliente(cliente);

        return resultados.stream().map(control ->
                new EstudioClinicoDTO(
                        control.getIdControl(),
                        control.getProducto().getNombre(),
                        control.getCliente().getNombre(),
                        control.getFecha(),
                        control.getResultado()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<EstudioClinicoDTO> buscarPorFecha(Date fechaInicio, Date fechaFin) {
        List<Control_Calidad> resultados = controlCalidadRepository.findByFecha(fechaInicio, fechaFin);

        return resultados.stream().map(control ->
                new EstudioClinicoDTO(
                        control.getIdControl(),
                        control.getProducto().getNombre(),
                        control.getCliente().getNombre(),
                        control.getFecha(),
                        control.getResultado()
                )
        ).collect(Collectors.toList());
    }
}