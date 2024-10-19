package com.megalabsapi.service;

import com.megalabsapi.dto.EntregaMuestraDTO;

import java.util.List;

public interface EntregaMuestraService {
    List<EntregaMuestraDTO> obtenerEntregasPorCliente(String ruc);
}
