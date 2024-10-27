package com.megalabsapi.api;

import com.megalabsapi.dto.EntregaMuestraDTO;
import com.megalabsapi.model.entity.Entrega_Muestra;

import com.megalabsapi.service.EntregaMuestraService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entrega-muestra")
public class EntregaMuestraController {

    private final EntregaMuestraService entregaMuestraService;

    @GetMapping
    public ResponseEntity<List<Entrega_Muestra>> getAllEntregaMuestra() {
        return ResponseEntity.ok(entregaMuestraService.getAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Entrega_Muestra>> paginateEntregaMuestra(
            @PageableDefault(size = 1,sort = "id") Pageable pageable) {
        Page<Entrega_Muestra> entregaMuestra = entregaMuestraService.paginate(pageable);
        return new ResponseEntity<Page<Entrega_Muestra>>(entregaMuestra, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega_Muestra> getEntregaMuestraById(@PathVariable("id") Integer id) {
        Entrega_Muestra entregaMuestra = entregaMuestraService.findById(id);
        return new ResponseEntity<Entrega_Muestra>(entregaMuestra, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Entrega_Muestra> createEntregaMuestra(@RequestBody Entrega_Muestra entregaMuestra) {
        Entrega_Muestra newEntregaMuestra = entregaMuestraService.create(entregaMuestra);
        return new ResponseEntity<Entrega_Muestra>(newEntregaMuestra, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrega_Muestra> updateEntregaMuestra(@PathVariable("id") Integer id,
                                                                @RequestBody Entrega_Muestra entregaMuestra) {
        Entrega_Muestra updateEntregaMuestra = entregaMuestraService.update(id,entregaMuestra);
        return new ResponseEntity<Entrega_Muestra>(updateEntregaMuestra, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entrega_Muestra> deleteEntregaMuestra(@PathVariable("id") Integer id) {
        entregaMuestraService.delete(id);
        return new ResponseEntity<Entrega_Muestra>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filtrar/muestra")
    public ResponseEntity<List<Entrega_Muestra>> findByStatus(@RequestParam("RUC") String RUC) {
        List<Entrega_Muestra> entregas = entregaMuestraService.findByClienteRuc(RUC);
        return ResponseEntity.ok(entregas);
    }

    @GetMapping("/filtrar/fecha")
    public ResponseEntity<List<Entrega_Muestra>> findByFecha(@RequestParam("fechaVenta")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        List<Entrega_Muestra> fechaFiltro = entregaMuestraService.findByFecha(fecha);
        return ResponseEntity.ok(fechaFiltro);
    }




    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<List<EntregaMuestraDTO>> obtenerEntregasPorCliente(@PathVariable String ruc) {
        List<EntregaMuestraDTO> entregas = entregaMuestraService.obtenerEntregasPorCliente(ruc);
        return ResponseEntity.ok(entregas);
    }
}