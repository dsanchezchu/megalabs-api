package com.megalabsapi.api;

import com.megalabsapi.dto.EnviarReporteRequest;
import com.megalabsapi.model.entity.ReporteCumplimientoRegulatorio;
import com.megalabsapi.service.NotificationService;
import com.megalabsapi.service.ReporteCumplimientoRegulatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes/cumplimiento-regulatorio")
public class ReporteCumplimientoRegulatorioController {

    private final ReporteCumplimientoRegulatorioService cumplimientoService;
    private final NotificationService notificationService;

    @Autowired
    public ReporteCumplimientoRegulatorioController(ReporteCumplimientoRegulatorioService cumplimientoService, NotificationService notificationService) {
        this.cumplimientoService = cumplimientoService;
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping("/crear")
    public ResponseEntity<ReporteCumplimientoRegulatorio> crearReporte(@RequestBody ReporteCumplimientoRegulatorio reporte) {
        return ResponseEntity.ok(cumplimientoService.crearReporte(reporte));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<ReporteCumplimientoRegulatorio> aprobarReporte(@PathVariable Integer id, @RequestBody boolean aprobado) {
        return ResponseEntity.ok(cumplimientoService.aprobarReporte(id, aprobado));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/{id}/generar-pdf")
    public ResponseEntity<String> generarReporteParaAutoridades(@PathVariable Integer id) {
        return ResponseEntity.ok(cumplimientoService.generarReporteParaAutoridades(id));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PutMapping("/enviar")
    public ResponseEntity<ReporteCumplimientoRegulatorio> enviarReporte(@RequestBody EnviarReporteRequest request) {
        ReporteCumplimientoRegulatorio reporte = cumplimientoService.enviarReporte(request.getId());

        // Enviar notificación al destinatario
        String email = request.getEmail();
        String subject = "Reporte de Cumplimiento Regulatorio Enviado";
        String message = "El reporte de cumplimiento regulatorio con ID " + reporte.getId() + " ha sido enviado para su revisión.";
        notificationService.sendEmail(email, subject, message);

        return ResponseEntity.ok(reporte);
    }


    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/enviados")
    public ResponseEntity<List<ReporteCumplimientoRegulatorio>> obtenerReportesEnviados() {
        return ResponseEntity.ok(cumplimientoService.obtenerReportesEnviados());
    }


    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/todos")
    public ResponseEntity<List<ReporteCumplimientoRegulatorio>> obtenerTodosLosReportes() {
        return ResponseEntity.ok(cumplimientoService.obtenerTodos());
    }
}

