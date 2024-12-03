package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.repository.ClienteRepository;
import com.megalabsapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente registrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorRuc(String ruc) {
        return clienteRepository.findById(ruc).orElseThrow(() ->
                new IllegalArgumentException("Cliente con RUC " + ruc + " no encontrado."));
    }

    @Override
    public Cliente actualizarCliente(String ruc, Cliente cliente) {
        Cliente existente = obtenerClientePorRuc(ruc);
        existente.setNombre(cliente.getNombre());
        existente.setLicencia(cliente.getLicencia());
        existente.setEspecializacion(cliente.getEspecializacion());
        existente.setEmail(cliente.getEmail());
        return clienteRepository.save(existente);
    }

    @Override
    public void eliminarCliente(String ruc) {
        if (!clienteRepository.existsById(ruc)) {
            throw new IllegalArgumentException("Cliente con RUC " + ruc + " no encontrado.");
        }
        clienteRepository.deleteById(ruc);
    }
}
