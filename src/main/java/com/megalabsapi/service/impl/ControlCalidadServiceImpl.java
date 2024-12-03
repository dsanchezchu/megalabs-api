package com.megalabsapi.service.impl;

import com.megalabsapi.dto.ActualizarEstudioClinicoDTO;
import com.megalabsapi.dto.EstudioClinicoDTO;
import com.megalabsapi.dto.RegistroEstudioClinicoDTO;
import com.megalabsapi.model.entity.Cliente;
import com.megalabsapi.model.entity.Control_Calidad;
import com.megalabsapi.model.entity.MetodoAnalitico;
import com.megalabsapi.model.entity.Producto;
import com.megalabsapi.model.enums.ControlCalidadStatus;
import com.megalabsapi.repository.ClienteRepository;
import com.megalabsapi.repository.ControlCalidadRepository;
import com.megalabsapi.repository.MetodoAnaliticoRepository;
import com.megalabsapi.repository.ProductoRepository;
import com.megalabsapi.service.ControlCalidadService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ControlCalidadServiceImpl implements ControlCalidadService {

    @Autowired
    private ControlCalidadRepository controlCalidadRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MetodoAnaliticoRepository metodoAnaliticoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public List<EstudioClinicoDTO> buscarPorProducto(String producto) {
        List<Control_Calidad> resultados = controlCalidadRepository.findByProducto(producto);

        // Convertir los resultados a DTOs
        return resultados.stream().map(control ->
                new EstudioClinicoDTO(
                        control.getIdControl(),
                        control.getProducto().getNombre(),
                        control.getCliente().getNombre(),  // Suponiendo que ahora haya una relación directa con Cliente
                        control.getFecha(),  // Utiliza 'fecha' de Control_Calidad
                        control.getResultado(),
                        control.getEstado(),
                        control.getMetodosAnaliticos().stream()
                                .map(MetodoAnalitico::getNombre)
                                .collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<EstudioClinicoDTO> buscarPorCliente(String cliente) {
        List<Control_Calidad> resultados = controlCalidadRepository.findByCliente(cliente);

        return resultados.stream().map(control ->
                new EstudioClinicoDTO(
                        control.getIdControl(),
                        control.getProducto().getNombre(),
                        control.getCliente().getNombre(),  // Suponiendo que ahora haya una relación directa con Cliente
                        control.getFecha(),  // Utiliza 'fecha' de Control_Calidad
                        control.getResultado(),
                        control.getEstado(),
                        control.getMetodosAnaliticos().stream()
                                .map(MetodoAnalitico::getNombre)
                                .collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<EstudioClinicoDTO> buscarPorFecha(Date fechaInicio, Date fechaFin) {
        List<Control_Calidad> resultados = controlCalidadRepository.findByFecha(fechaInicio, fechaFin);

        return resultados.stream().map(control ->
                new EstudioClinicoDTO(
                        control.getIdControl(),
                        control.getProducto().getNombre(),
                        control.getCliente().getNombre(),  // Suponiendo que ahora haya una relación directa con Cliente
                        control.getFecha(),  // Utiliza 'fecha' de Control_Calidad
                        control.getResultado(),
                        control.getEstado(),
                        control.getMetodosAnaliticos().stream()
                                .map(MetodoAnalitico::getNombre)
                                .collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }

    @Override
    public void registrarEstudioClinico(RegistroEstudioClinicoDTO registroDTO) {
        // Validar el producto
        Producto producto = productoRepository.findById(registroDTO.getProductoId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado."));

        // Validar los métodos analíticos
        List<MetodoAnalitico> metodosAnaliticos = metodoAnaliticoRepository.findAllById(registroDTO.getMetodosAnaliticosIds());
        if (metodosAnaliticos.size() != registroDTO.getMetodosAnaliticosIds().size()) {
            throw new EntityNotFoundException("Algunos métodos analíticos no existen.");
        }

        // Validar el cliente
        Cliente cliente = clienteRepository.findByRuc(registroDTO.getClienteRuc())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado."));

        // Crear y llenar la entidad ControlCalidad
        Control_Calidad controlCalidad = new Control_Calidad();
        controlCalidad.setProducto(producto);
        controlCalidad.setFecha(Date.valueOf(registroDTO.getFecha())); // Convertir LocalDate a Date
        controlCalidad.setResultado(registroDTO.getResultado());
        controlCalidad.setMetodosAnaliticos(metodosAnaliticos);
        controlCalidad.setEstado(registroDTO.getEstado() != null ? registroDTO.getEstado() : ControlCalidadStatus.EN_PRUEBAS);
        controlCalidad.setCliente(cliente); // Asignar el cliente

        // Guardar la entidad en la base de datos
        controlCalidadRepository.save(controlCalidad);
    }

    @Override
    public void actualizarEstudioClinico(ActualizarEstudioClinicoDTO actualizarDTO) {
        // Validar si existe el estudio clínico con el ID proporcionado
        Control_Calidad controlCalidad = controlCalidadRepository.findById(actualizarDTO.getIdControl())
                .orElseThrow(() -> new EntityNotFoundException("El estudio clínico con ID " + actualizarDTO.getIdControl() + " no existe."));

        // Validar el producto si se proporciona un nuevo ID de producto
        if (actualizarDTO.getProductoId() != null) {
            Producto producto = productoRepository.findById(actualizarDTO.getProductoId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID " + actualizarDTO.getProductoId()));
            controlCalidad.setProducto(producto); // Actualizar el producto
        }

        // Validar la fecha si se proporciona
        if (actualizarDTO.getFecha() != null) {
            controlCalidad.setFecha(Date.valueOf(actualizarDTO.getFecha())); // Actualizar la fecha
        }

        // Actualizar el resultado si se proporciona
        if (actualizarDTO.getResultado() != null) {
            controlCalidad.setResultado(actualizarDTO.getResultado());
        }

        // Actualizar los métodos analíticos si se proporcionan
        if (actualizarDTO.getMetodosAnaliticosIds() != null && !actualizarDTO.getMetodosAnaliticosIds().isEmpty()) {
            List<MetodoAnalitico> metodosAnaliticos = metodoAnaliticoRepository.findAllById(actualizarDTO.getMetodosAnaliticosIds());
            if (metodosAnaliticos.size() != actualizarDTO.getMetodosAnaliticosIds().size()) {
                throw new IllegalArgumentException("Algunos métodos analíticos no existen.");
            }
            controlCalidad.setMetodosAnaliticos(metodosAnaliticos); // Actualizar los métodos analíticos
        }

        // Actualizar el cliente si se proporciona un nuevo RUC
        if (actualizarDTO.getClienteRuc() != null) {
            Cliente cliente = clienteRepository.findByRuc(actualizarDTO.getClienteRuc())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con RUC " + actualizarDTO.getClienteRuc()));
            controlCalidad.setCliente(cliente); // Actualizar el cliente
        }

        // Actualizar el estado si se proporciona
        if (actualizarDTO.getEstado() != null) {
            controlCalidad.setEstado(actualizarDTO.getEstado());
        }

        // Guardar la entidad actualizada
        controlCalidadRepository.save(controlCalidad);
    }


}
