package com.megalabsapi.api;

import com.megalabsapi.service.DetalleVentaService;
import com.megalabsapi.model.entity.Detalle_Venta;
import com.megalabsapi.repository.DetalleVentaRepository;
import com.megalabsapi.service.impl.DetalleVentaServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/ventas")
public class DetalleVentaController {
    @Autowired
    private DetalleVentaServiceImpl detalleVentaService;
    private Detalle_Venta detalle_venta;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;



    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/reporte")
    public ResponseEntity<List<Object[]>> obtenerReporteVentas() {
        List<Object[]> reporte = detalleVentaService.obtenerReporteVentas();
        return ResponseEntity.ok(reporte);
    }


    @GetMapping("/correo/{id}")
    public ResponseEntity<String> mandarEmail(@PathVariable("id") Integer id){
        try {
                Detalle_Venta detalle_venta = detalleVentaService.obtenerDetalleVenda(id);
                detalleVentaService.sendDetalleVendaConfirmationEmil(detalle_venta);
                return ResponseEntity.ok("Correo enviado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar el email"+e.getMessage());
        }
    }
}