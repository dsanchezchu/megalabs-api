package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.model.entity.Orden;
import com.megalabsapi.model.enums.EstadoOrden;
import com.megalabsapi.repository.ClienteRepository;
import com.megalabsapi.repository.OrdenRepository;
import com.megalabsapi.service.NotificationService;
import com.megalabsapi.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final ClienteRepository clienteRepository;
    private final NotificationService notificationService;

    @Value("${notificaciones.correoInterno}")
    private String internalEmail;

    @Autowired
    public OrdenServiceImpl(OrdenRepository ordenRepository, ClienteRepository clienteRepository, NotificationService notificationService) {
        this.ordenRepository = ordenRepository;
        this.clienteRepository = clienteRepository;
        this.notificationService = notificationService;
    }

    @Override
    public Orden crearOrden(Orden orden) {
        // Busca el cliente solo usando el RUC
        Cliente cliente = clienteRepository.findById(orden.getCliente().getRuc())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        orden.setCliente(cliente); // Asocia el cliente existente a la orden
        return ordenRepository.save(orden);
    }

    @Override
    public Orden actualizarEstado(Integer id, EstadoOrden estado) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        orden.setEstado(estado);
        orden.setFechaActualizacion(new Date());
        return ordenRepository.save(orden);
    }

    @Override
    public Orden reportarProblema(Integer id, String problema) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        orden.setProblema(problema);
        orden.setFechaActualizacion(new Date());
        ordenRepository.save(orden);

        // Enviar notificación al cliente
        String subject = "Problema con su Orden ID: " + orden.getId();
        String message = "Ha ocurrido un problema con su orden: " + problema;
        notificationService.sendEmail(orden.getCliente().getEmail(), subject, message); // Usa el correo del cliente

        // Enviar notificación al equipo interno
        notificationService.sendEmail(internalEmail, "Problema en la Orden ID: " + orden.getId(), message);

        return orden;
    }
}
