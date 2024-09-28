package com.megalabsapi.service;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.enums.EntregaStatus;
import java.util.List;

public interface EntregaMuestraService {
    List<Entrega_Muestra> obtenerEntregasPorCliente(String ruc);
}
