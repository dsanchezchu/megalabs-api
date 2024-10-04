package com.megalabsapi.model.controller;

import com.megalabsapi.model.entity.Medicamento;
import com.megalabsapi.service.MedicamentoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {
    @Autowired
    private MedicamentoService medicamentoService;

    @GetMapping("/stock")
    public ResponseEntity<List<Medicamento>> getMedicamentosEnStock() {
        List<Medicamento> medicamentos = medicamentoService.obtenerMedicamentosEnStock();
        return ResponseEntity.ok(medicamentos);
    }

    @GetMapping("/reporte")
    public void generarReporte(HttpServletResponse response) throws Exception {
        List<Medicamento> medicamentos = medicamentoService.obtenerMedicamentosEnStock();
        // Código para generar PDF usando JasperReports
    }

    @PostMapping("/crear")
    public ResponseEntity<Medicamento> crearMedicamento(@RequestBody Medicamento medicamento) {
        Medicamento nuevoMedicamento = medicamentoService.guardarMedicamento(medicamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMedicamento);
    }
}