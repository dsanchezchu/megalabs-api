package com.megalabsapi.api;

import com.megalabsapi.dto.ProgramacionRecojoDTO;
import com.megalabsapi.service.ProgramacionRecojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programacion")
public class ProgramacionRecojoController {

    private final ProgramacionRecojoService programacionRecojoService;

    @Autowired
    public ProgramacionRecojoController(ProgramacionRecojoService programacionRecojoService) {
        this.programacionRecojoService = programacionRecojoService;
    }

    @GetMapping("/recojo")
    public List<ProgramacionRecojoDTO> obtenerProgramacionRecojo() {
        return programacionRecojoService.obtenerProgramacionRecojo();
    }

    @PostMapping("/recojo")
    public void crearProgramacionRecojo(@RequestBody ProgramacionRecojoDTO programacionDTO) {
        programacionRecojoService.crearProgramacionRecojo(programacionDTO);
    }

    @PostMapping("/recojo/confirmar/{id}")
    public void confirmarRecojo(@PathVariable Long id) {
        programacionRecojoService.confirmarRecojo(id);
    }

    @GetMapping("/recojo/cliente/{ruc}")
    public List<ProgramacionRecojoDTO> obtenerProgramacionPorRuc(@PathVariable String ruc) {
        return programacionRecojoService.obtenerProgramacionPorRuc(ruc);
    }
}