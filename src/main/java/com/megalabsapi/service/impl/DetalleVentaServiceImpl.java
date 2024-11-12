package com.megalabsapi.service.impl;

import com.megalabsapi.integration.notification.email.dto.Mail;
import com.megalabsapi.integration.notification.email.service.EmailService;
import com.megalabsapi.model.entity.Detalle_Venta;
import com.megalabsapi.repository.DetalleVentaRepository;
import com.megalabsapi.service.DetalleVentaService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final EmailService emailService;
    @Value("${spring.mail.username}")
    private String mainFrom;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    public List<Object[]> obtenerReporteVentas() {
        return detalleVentaRepository.obtenerReporteVentas();
    }

    @Override
    public Detalle_Venta obtenerDetalleVenda(int idDetalleVenta) {
        return detalleVentaRepository.findById(idDetalleVenta)
                .orElseThrow(()-> new RuntimeException("No se encontro el detalle de venta"));
    }

    public void sendDetalleVendaConfirmationEmil(Detalle_Venta detalleVenta) throws MessagingException {
        String userEmail = detalleVenta.getCliente().getEmail();
        Map<String, Object> model = new HashMap<>();
        model.put("clienteCorreo",userEmail);
        model.put("clienteNombre", detalleVenta.getCliente().getNombre());
        model.put("clienteRuc", detalleVenta.getCliente().getRuc());
        model.put("productoId",detalleVenta.getProducto().getIdProducto());
        model.put("productoNombre", detalleVenta.getProducto().getNombre());
        model.put("montoTotal",detalleVenta.getVenta().getPago().getMontoTotal());
        model.put("cantidad", detalleVenta.getCantidad());
        model.put("precio", detalleVenta.getPrecio());

        Mail mail = emailService.createMail(
                userEmail,
                "Confirmacion del detalle de venta",
                model,
                mainFrom
        );
        emailService.sendEmail(mail, "confirmation.html");
    }
}
