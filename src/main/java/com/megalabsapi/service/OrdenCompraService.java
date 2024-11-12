package com.megalabsapi.service;

import com.megalabsapi.model.entity.OrdenCompra;
import com.megalabsapi.model.entity.Proveedor;
import com.megalabsapi.repository.OrdenCompraRepository;
import com.megalabsapi.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenCompraService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final ProveedorRepository proveedorRepository;

    @Autowired
    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository, ProveedorRepository proveedorRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public OrdenCompra crearOrdenCompra(Long proveedorId, String nombreMateriaPrima, Integer cantidad, Double costoTotal, Integer tiempoEntrega) {
        Proveedor proveedor = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setProveedor(proveedor);
        ordenCompra.setNombreMateriaPrima(nombreMateriaPrima);
        ordenCompra.setCantidadSolicitada(cantidad);
        ordenCompra.setCostoTotal(costoTotal);
        ordenCompra.setTiempoEstimadoEntrega(tiempoEntrega);
        ordenCompra.setEstado("No entregado");

        return ordenCompraRepository.save(ordenCompra);
    }
}