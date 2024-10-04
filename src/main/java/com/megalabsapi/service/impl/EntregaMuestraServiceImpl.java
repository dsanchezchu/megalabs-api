package com.megalabsapi.service.impl;

import com.megalabsapi.dto.EntregaMuestraDTO;
import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.enums.EntregaStatus;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.service.EntregaMuestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregaMuestraServiceImpl implements EntregaMuestraService {

    @Autowired
    private EntregaMuestraRepository entregaMuestraRepository;

    public EntregaMuestraServiceImpl(EntregaMuestraRepository entregaMuestraRepository) {
        this.entregaMuestraRepository = entregaMuestraRepository;
    }

    @Override
    public List<EntregaMuestraDTO> obtenerEntregasPorCliente(String ruc) {
        List<Entrega_Muestra> entregas = entregaMuestraRepository.findByClienteRuc(ruc);

        // Convertir la lista de Entrega_Muestra a EntregaMuestraDTO
        return entregas.stream()
                .map(entrega -> new EntregaMuestraDTO(
                        entrega.getIdEntrega(),
                        entrega.getLugar(),
                        entrega.getFecha(),
                        entrega.getEstado().name() // Usamos name() para obtener el String del Enum
                ))
                .collect(Collectors.toList());
    }

}