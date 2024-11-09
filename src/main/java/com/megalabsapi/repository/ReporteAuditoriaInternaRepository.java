package com.megalabsapi.repository;

import com.megalabsapi.model.entity.ReporteAuditoriaInterna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteAuditoriaInternaRepository extends JpaRepository<ReporteAuditoriaInterna, Integer> {
    // Si necesitas un método específico para obtener solo reportes enviados, podrías añadir algo como:
    List<ReporteAuditoriaInterna> findByEnviado(boolean enviado);
}

