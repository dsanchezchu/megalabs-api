package com.megalabsapi.service;

import com.megalabsapi.model.entity.EstudioClinico;

import com.megalabsapi.repository.EstudioClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudioClinicoService {
    @Autowired
    private EstudioClinicoRepository estudioClinicoRepository;

    public List<EstudioClinico> obtenerTodosLosEstudios() {
        return estudioClinicoRepository.findAll();
    }

    public EstudioClinico guardarEstudio(EstudioClinico estudioClinico) {
        return estudioClinicoRepository.save(estudioClinico);
    }

    public EstudioClinico obtenerEstudioPorId(Long id) {
        return estudioClinicoRepository.findById(id).orElse(null);
    }

    public void eliminarEstudio(Long id) {
        estudioClinicoRepository.deleteById(id);
    }
}