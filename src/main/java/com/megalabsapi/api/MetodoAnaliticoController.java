package com.megalabsapi.api;

import com.megalabsapi.model.entity.Control_Calidad;
import com.megalabsapi.model.entity.MetodoAnalitico;
import com.megalabsapi.repository.ControlCalidadRepository;
import com.megalabsapi.service.MetodoAnaliticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/metodoAnalitico")
public class MetodoAnaliticoController {

    @Autowired
    private MetodoAnaliticoService metodoAnaliticoService;

    @Autowired
    private ControlCalidadRepository controlCalidadRepository;  // Repositorio de ControlCalidad

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/listarPorControlCalidad/{idControlCalidad}")
    public List<MetodoAnalitico> listarMetodosPorControlCalidad(@PathVariable Integer idControlCalidad) {
        Optional<Control_Calidad> controlCalidad = controlCalidadRepository.findById(idControlCalidad);
        if (controlCalidad.isPresent()) {
            return metodoAnaliticoService.obtenerMetodosPorControlCalidad(controlCalidad.get());
        } else {
            throw new RuntimeException("Control de calidad no encontrado.");
        }
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/{id}")
    public Optional<MetodoAnalitico> obtenerMetodoPorId(@PathVariable Integer id) {
        return metodoAnaliticoService.obtenerMetodoPorId(id);
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping("/crear")
    public MetodoAnalitico crearMetodoAnalitico(@RequestBody MetodoAnalitico metodoAnalitico) {
        return metodoAnaliticoService.crearMetodoAnalitico(metodoAnalitico);
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/resumen/{id}")
    public String generarResumen(@PathVariable Integer id) {
        Optional<MetodoAnalitico> metodo = metodoAnaliticoService.obtenerMetodoPorId(id);
        return metodo.map(metodoAnaliticoService::generarResumenMetodo)
                .orElse("Método analítico no encontrado.");
    }
}