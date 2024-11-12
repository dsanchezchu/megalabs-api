package com.megalabsapi.api;

import com.megalabsapi.dto.AuditoriaDTO;
import com.megalabsapi.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    @Autowired
    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @GetMapping("/admin/auditorias")
    public List<AuditoriaDTO> verAuditorias() {
        return auditoriaService.obtenerAuditorias();
    }

    @PostMapping("/admin/auditorias/registrar")
    public void registrarAuditoria(@RequestParam String accion,
                                   @RequestParam String pantalla,
                                   @RequestParam String usuario) {
        auditoriaService.registrarAccion(accion, pantalla, usuario);
    }
}
