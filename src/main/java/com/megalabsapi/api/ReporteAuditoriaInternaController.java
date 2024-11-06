package com.megalabsapi.api;

import com.megalabsapi.dto.EnviarReporteRequest;
import com.megalabsapi.model.entity.ReporteAuditoriaInterna;
import com.megalabsapi.service.NotificationService;
import com.megalabsapi.service.ReporteAuditoriaInternaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes/auditoria-interna")
public class ReporteAuditoriaInternaController {

    private final ReporteAuditoriaInternaService auditoriaService;
    private final NotificationService notificationService;

    @Autowired
    public ReporteAuditoriaInternaController(ReporteAuditoriaInternaService auditoriaService, NotificationService notificationService) {
        this.auditoriaService = auditoriaService;
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping("/crear")
    public ResponseEntity<ReporteAuditoriaInterna> crearReporte(@RequestBody ReporteAuditoriaInterna auditoria) {
        return ResponseEntity.ok(auditoriaService.crearReporte(auditoria));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PutMapping("/{id}/clasificar-impacto")
    public ResponseEntity<ReporteAuditoriaInterna> clasificarImpacto(@PathVariable Integer id, @RequestBody String impacto) {
        return ResponseEntity.ok(auditoriaService.clasificarImpacto(id, impacto));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PutMapping("/{id}/inconformidades-recomendaciones")
    public ResponseEntity<ReporteAuditoriaInterna> registrarInconformidadesYRecomendaciones(
            @PathVariable Integer id,
            @RequestParam String inconformidades,
            @RequestParam String recomendaciones) {
        return ResponseEntity.ok(auditoriaService.registrarInconformidadesYRecomendaciones(id, inconformidades, recomendaciones));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PutMapping("/enviar")
    public ResponseEntity<ReporteAuditoriaInterna> enviarReporte(@RequestBody EnviarReporteRequest request) {
        ReporteAuditoriaInterna reporte = auditoriaService.enviarReporte(request.getId());

        // Enviar notificación al destinatario
        String email = request.getEmail();
        String subject = "Reporte de Auditoría Interna Enviado";
        String message = "El reporte de auditoría interna con ID " + reporte.getId() + " ha sido enviado para su revisión.";
        notificationService.sendEmail(email, subject, message);

        return ResponseEntity.ok(reporte);
    }


    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/enviados")
    public ResponseEntity<List<ReporteAuditoriaInterna>> obtenerReportesEnviados() {
        return ResponseEntity.ok(auditoriaService.obtenerReportesEnviados());
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/todos")
    public ResponseEntity<List<ReporteAuditoriaInterna>> obtenerTodosLosReportes() {
        return ResponseEntity.ok(auditoriaService.obtenerTodos());
    }
}

