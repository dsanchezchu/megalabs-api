package com.megalabsapi.api;

import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.getClientes());
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PostMapping
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.registrarCliente(cliente));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @GetMapping("/{ruc}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String ruc) {
        return ResponseEntity.ok(clienteService.obtenerClientePorRuc(ruc));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @PutMapping("/{ruc}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable String ruc, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizarCliente(ruc, cliente));
    }

    @PreAuthorize("hasRole('ROLE_REPRESENTANTE')")
    @DeleteMapping("/{ruc}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String ruc) {
        clienteService.eliminarCliente(ruc);
        return ResponseEntity.noContent().build();
    }
}