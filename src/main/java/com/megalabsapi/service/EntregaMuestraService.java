package com.megalabsapi.service;

import com.megalabsapi.dto.EntregaMuestraDTO;
import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.enums.EntregaStatus;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface EntregaMuestraService {
    List<Entrega_Muestra> getAll();
    @Transactional(readOnly = true)
    Page<Entrega_Muestra> paginate(org.springframework.data.domain.Pageable pageable);
    List<Entrega_Muestra> findByFecha(Date fecha);
    Entrega_Muestra create(Entrega_Muestra entregaMuestra);
    Entrega_Muestra findById(Integer id);
    Entrega_Muestra update(Integer id, Entrega_Muestra updateEntregaMuestra);
    void delete(Integer id);
    List<EntregaMuestraDTO> obtenerEntregasPorCliente(String ruc);
}
