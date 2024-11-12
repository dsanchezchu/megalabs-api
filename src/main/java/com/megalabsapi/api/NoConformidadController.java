package com.megalabsapi.api;

import com.megalabsapi.model.entity.Entrega_Muestra;
import com.megalabsapi.model.entity.NoConformidad;
import com.megalabsapi.repository.EntregaMuestraRepository;
import com.megalabsapi.service.NoConformidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/noConformidad")
public class NoConformidadController {

    @Autowired
    private NoConformidadService noConformidadService;

    @Autowired
    private EntregaMuestraRepository entregaMuestraRepository;  // Asumiendo que tienes este repositorio

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/porEntrega/{entregaMuestraId}")
    public List<NoConformidad> obtenerNoConformidadesPorEntrega(@PathVariable Integer entregaMuestraId) {
        Optional<Entrega_Muestra> entregaMuestra = entregaMuestraRepository.findById(entregaMuestraId);  // Buscar la entidad por ID
        if (entregaMuestra.isPresent()) {
            return noConformidadService.obtenerNoConformidadesPorEntrega(entregaMuestra.get());  // Pasar la entidad completa
        } else {
            throw new RuntimeException("Entrega de muestra no encontrada.");
        }
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/porCliente/{ruc}")
    public List<NoConformidad> obtenerNoConformidadesPorCliente(@PathVariable String ruc) {
        return noConformidadService.obtenerNoConformidadesPorCliente(ruc);  // Llamada al servicio ajustado
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/{id}")
    public Optional<NoConformidad> obtenerNoConformidadPorId(@PathVariable Integer id) {
        return noConformidadService.buscarPorId(id);
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/criticas")
    public List<NoConformidad> obtenerNoConformidadesCriticas() {
        return noConformidadService.obtenerNoConformidadesCriticas();
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping("/crear")
    public NoConformidad crearNoConformidad(@RequestBody NoConformidad noConformidad) {
        NoConformidad nuevaNoConformidad = noConformidadService.crearNoConformidad(noConformidad);
        if (nuevaNoConformidad.getCritico()) {
            noConformidadService.enviarNotificacionCritica(nuevaNoConformidad);
        }
        return nuevaNoConformidad;
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/reporte/{id}")
    public String generarReporteCliente(@PathVariable Integer id) {
        Optional<NoConformidad> noConformidad = noConformidadService.buscarPorId(id);
        return noConformidad.map(noConformidadService::generarReporteCliente)
                .orElse("No Conformidad no encontrada.");
    }
}

