package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Medicamento;
import com.megalabsapi.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public List<Medicamento> obtenerMedicamentosEnStock() {
        return medicamentoRepository.findByCantidadGreaterThan(0);
    }

    public Medicamento guardarMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }
}