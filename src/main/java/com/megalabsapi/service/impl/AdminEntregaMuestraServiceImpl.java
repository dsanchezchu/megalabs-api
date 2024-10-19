package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.service.AdminEntregaMuestraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEntregaMuestraServiceImpl implements AdminEntregaMuestraService {
    private final EntregaMuestraRepository entregaMuestraRepository;

    @Override
    public List<Entrega_Muestra> findByFecha(Date fecha) {
        return entregaMuestraRepository.findByFecha(fecha);
    }

    @Override
    public List<Entrega_Muestra> getAll() {
        return entregaMuestraRepository.findAll();
    }

    @Override
    public List<Entrega_Muestra> findByClienteRuc(String RUC) {
        return entregaMuestraRepository.findByClienteRuc(RUC);
    }

    @Override
    public Page<Entrega_Muestra> paginate(Pageable pageable) {
        return entregaMuestraRepository.findAll(pageable);
    }

    @Override
    public Entrega_Muestra findById(Integer id) {
        return entregaMuestraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega_Muestra no encontrada"));
    }

    @Override
    public Entrega_Muestra create(Entrega_Muestra entregaMuestra) {
        entregaMuestra.setCreatedAt(LocalDateTime.now());  // Establecer created_at antes de guardar
        return entregaMuestraRepository.save(entregaMuestra);
    }

    @Override
    public Entrega_Muestra update(Integer id, Entrega_Muestra updateEntregaMuestra) {
        Entrega_Muestra entregaMuestra = findById(id);
        // Actualizar campos...
        return entregaMuestraRepository.save(entregaMuestra);
    }

    @Override
    public void delete(Integer id) {
        Entrega_Muestra entregaMuestra = findById(id);
        entregaMuestraRepository.delete(entregaMuestra);
    }
}