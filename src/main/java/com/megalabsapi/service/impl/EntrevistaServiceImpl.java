package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Entrevista;
import com.megalabsapi.repository.EntrevistaRepository;
import com.megalabsapi.service.EntrevistaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class EntrevistaServiceImpl implements EntrevistaService {


    @Autowired
    private EntrevistaRepository entrevistaRepository;

    @Override
    public List<Entrevista> obtenerEntrevistasPorRepresentante(String dniRepresentante) {
        return entrevistaRepository.findByRepresentanteDni(dniRepresentante);
    }

    @Override
    public Entrevista crearEntrevista(Entrevista entrevista) {
        return entrevistaRepository.save(entrevista);
    }

    @Override
    public Entrevista actualizarEntrevista(Integer idEntrevista, Entrevista entrevista) {
        entrevista.setIdEntrevista(idEntrevista);
        return entrevistaRepository.save(entrevista);
    }

    @Override
    public void eliminarEntrevista(Integer idEntrevista) {
        entrevistaRepository.deleteById(idEntrevista);
    }

    @Override
    public List<Entrevista> obtenerTodasLasEntrevistas() {
        return entrevistaRepository.findAll();
    }

    @Override
    public List<Entrevista> obtenerEntrevistasPorFecha(Date fecha) {
        return entrevistaRepository.findByFecha(fecha);
    }

    @Override
    public List<Entrevista> obtenerEntrevistasPorSede(String lugarSede) {
        return entrevistaRepository.findByLugarSede(lugarSede);
    }

    @Override
    public List<Entrevista> obtenerEntrevistasPorCliente(String rucCliente) {
        return entrevistaRepository.findByClienteRuc(rucCliente);
    }
}