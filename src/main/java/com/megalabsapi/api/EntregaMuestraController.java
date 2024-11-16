package com.megalabsapi.api;

import com.megalabsapi.dto.EntregaMuestraDTO;
import com.megalabsapi.service.EntregaMuestraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entrega-muestra")
public class EntregaMuestraController {

    private final EntregaMuestraService entregaMuestraService;

    @GetMapping
    public List<EntregaMuestraDTO> getAllEntregaMuestra() {
        return entregaMuestraService.getAll();
    }

    @GetMapping("/page")
    public Page<EntregaMuestraDTO> paginateEntregaMuestra(Pageable pageable) {
        return entregaMuestraService.paginate(pageable);
    }

    @GetMapping("/{id}")
    public EntregaMuestraDTO getEntregaMuestraById(@PathVariable Integer id) {
        return entregaMuestraService.findById(id);
    }

    @PostMapping
    public EntregaMuestraDTO createEntregaMuestra(@RequestBody EntregaMuestraDTO entregaMuestraDTO) {
        return entregaMuestraService.create(entregaMuestraDTO);
    }

    @PutMapping("/{id}")
    public EntregaMuestraDTO updateEntregaMuestra(@PathVariable Integer id,
                                                  @RequestBody EntregaMuestraDTO entregaMuestraDTO) {
        return entregaMuestraService.update(id, entregaMuestraDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEntregaMuestra(@PathVariable Integer id) {
        entregaMuestraService.delete(id);
    }

    @GetMapping("/filtrar/muestra")
    public List<EntregaMuestraDTO> findByClienteRuc(@RequestParam String ruc) {
        return entregaMuestraService.findByClienteRuc(ruc);
    }

    @GetMapping("/filtrar/fecha")
    public List<EntregaMuestraDTO> findByFecha(@RequestParam Date fecha) {
        return entregaMuestraService.findByFecha(fecha);
    }
}
