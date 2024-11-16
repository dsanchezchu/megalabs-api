package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Medicamento;
import com.megalabsapi.repository.MedicamentoRepository;
import com.megalabsapi.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    public List<Medicamento> obtenerMedicamentosEnStock() {
        return medicamentoRepository.findByCantidadGreaterThan(0);
    }

    @Override
    public Medicamento guardarMedicamento(Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }
}
