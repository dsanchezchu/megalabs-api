package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByCantidadGreaterThan(int cantidad);
}

