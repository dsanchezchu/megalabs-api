package com.megalabsapi.repository;

import com.megalabsapi.model.entity.ReporteCumplimientoRegulatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteCumplimientoRegulatorioRepository extends JpaRepository<ReporteCumplimientoRegulatorio, Integer> {
    // Método para obtener reportes enviados, si es necesario
    List<ReporteCumplimientoRegulatorio> findByEnviado(boolean enviado);
}

