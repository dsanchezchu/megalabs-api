package com.megalabsapi.service;

import com.megalabsapi.model.entity.Medicamento;
import java.util.List;

public interface MedicamentoService {
    List<Medicamento> obtenerMedicamentosEnStock();
    Medicamento guardarMedicamento(Medicamento medicamento);
}
