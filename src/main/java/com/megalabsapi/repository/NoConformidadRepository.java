package com.megalabsapi.repository;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.entity.NoConformidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoConformidadRepository extends JpaRepository<NoConformidad, Integer> {
    List<NoConformidad> findByEntregaMuestra(Entrega_Muestra entregaMuestra);  // Cambia a buscar por la entidad completa
    List<NoConformidad> findByCritico(Boolean critico);  // Para no conformidades críticas
}

