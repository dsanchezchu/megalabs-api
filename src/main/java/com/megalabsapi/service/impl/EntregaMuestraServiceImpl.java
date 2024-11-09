package com.megalabsapi.service.impl;

import com.megalabsapi.dto.EntregaMuestraDTO;
import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.service.EntregaMuestraService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EntregaMuestraServiceImpl implements EntregaMuestraService {

    private final EntregaMuestraRepository entregaMuestraRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Entrega_Muestra> getAll() {
        //Metodos propipos de repositorio
        return entregaMuestraRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Entrega_Muestra> paginate(Pageable pageable) {
        return entregaMuestraRepository.findAll(pageable);
    }

    @Override
    public List<Entrega_Muestra> findByFecha(Date fecha) {
        return entregaMuestraRepository.findByFecha(fecha);
    }

    @Transactional
    @Override
    public Entrega_Muestra create(Entrega_Muestra entregaMuestra) {
        entregaMuestra.setCreatedAt(LocalDateTime.now());
        return entregaMuestraRepository.save(entregaMuestra);
    }

    @Transactional(readOnly = true)
    @Override
    public Entrega_Muestra findById(Integer id) {
        return entregaMuestraRepository.findById(id).orElseThrow(()-> new RuntimeException("Entrega-Muestra no funciona"));
    }

    @Transactional
    @Override
    public Entrega_Muestra update(Integer id, Entrega_Muestra updateEntregaMuestra) {
        Entrega_Muestra entregaMuestraFromDB = findById(id);
        entregaMuestraFromDB.setUpdatedAt(LocalDateTime.now());
        entregaMuestraFromDB.setLugar(updateEntregaMuestra.getLugar());
        entregaMuestraFromDB.setFecha(updateEntregaMuestra.getFecha());
        entregaMuestraFromDB.setEstado(updateEntregaMuestra.getEstado());
        entregaMuestraFromDB.setProducto(updateEntregaMuestra.getProducto());
        entregaMuestraFromDB.setCliente(updateEntregaMuestra.getCliente());
        return entregaMuestraRepository.save(entregaMuestraFromDB);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Entrega_Muestra entregaMuestra = findById(id);
        entregaMuestraRepository.delete(entregaMuestra);
    }

    @Override
    public List<Entrega_Muestra> findByClienteRuc(String RUC) {
        return entregaMuestraRepository.findByClienteRuc(RUC);
    }

    @Override
    public List<EntregaMuestraDTO> obtenerEntregasPorCliente(String ruc) {
        List<Entrega_Muestra> entregas = entregaMuestraRepository.findByClienteRuc(ruc);

        // Convertir la lista de Entrega_Muestra a EntregaMuestraDTO
        return entregas.stream()
                .map(entrega -> new EntregaMuestraDTO(
                        entrega.getId(),
                        entrega.getLugar(),
                        entrega.getFecha(),
                        entrega.getEstado().name() // Usamos name() para obtener el String del Enum
                ))
                .collect(Collectors.toList());
    }

}


