package com.megalabsapi.service;

import com.megalabsapi.model.entity.Detalle_Venta;
import jakarta.mail.MessagingException;

import java.util.List;

public interface DetalleVentaService {

    List<Object[]> obtenerReporteVentas();
    void sendDetalleVendaConfirmationEmil(Detalle_Venta detalleVenta) throws MessagingException;
    Detalle_Venta obtenerDetalleVenda(int idDetalleVenta) ;
}
