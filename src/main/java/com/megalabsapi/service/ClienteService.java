package com.megalabsapi.service;

import com.megalabsapi.model.entity.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> getClientes();
    Cliente registrarCliente(Cliente cliente);
    Cliente obtenerClientePorRuc(String ruc);
    Cliente actualizarCliente(String ruc, Cliente cliente);
    void eliminarCliente(String ruc);
}
