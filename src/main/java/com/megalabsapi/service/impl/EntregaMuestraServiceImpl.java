package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.enums.EntregaStatus;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.service.EntregaMuestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaMuestraServiceImpl implements EntregaMuestraService {

    private final EntregaMuestraRepository entregaMuestraRepository;

    public EntregaMuestraServiceImpl(EntregaMuestraRepository entregaMuestraRepository) {
        this.entregaMuestraRepository = entregaMuestraRepository;
    }

    @Override
    public List<Entrega_Muestra> obtenerEntregasPorCliente(String ruc) {
        return entregaMuestraRepository.findByClienteRuc(ruc);
    }
}