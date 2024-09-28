package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Control_Calidad;
import com.megalabsapi.repository.ControlCalidadRepository;
import com.megalabsapi.service.ControlCalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ControlCalidadServiceImpl implements ControlCalidadService {

    @Autowired
    private ControlCalidadRepository controlCalidadRepository;

    @Override
    public List<Control_Calidad> buscarPorProducto(Integer idProducto) {
        return controlCalidadRepository.findByProductoIdProducto(idProducto);
    }

    @Override
    public Control_Calidad guardarControlCalidad(Control_Calidad controlCalidad) {
        return controlCalidadRepository.save(controlCalidad);
    }

    @Override
    public Control_Calidad actualizarControlCalidad(Integer idControl, Control_Calidad controlCalidad) {
        controlCalidad.setIdControl(idControl);
        return controlCalidadRepository.save(controlCalidad);
    }

    @Override
    public void eliminarControlCalidad(Integer idControl) {
        controlCalidadRepository.deleteById(idControl);
    }

    @Override
    public List<Control_Calidad> listarControlesCalidad() {
        return controlCalidadRepository.findAll();
    }
}