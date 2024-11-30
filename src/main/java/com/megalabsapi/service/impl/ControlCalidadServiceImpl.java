package com.megalabsapi.service.impl;

import com.megalabsapi.dto.EstudioClinicoDTO;
import com.megalabsapi.dto.RegistroEstudioClinicoDTO;
import com.megalabsapi.model.entity.Control_Calidad;
import com.megalabsapi.model.entity.MetodoAnalitico;
import com.megalabsapi.model.entity.Producto;
import com.megalabsapi.repository.ControlCalidadRepository;
import com.megalabsapi.repository.MetodoAnaliticoRepository;
import com.megalabsapi.repository.ProductoRepository;
import com.megalabsapi.service.ControlCalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
                        control.getResultado()
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
                        control.getCliente().getNombre(),
                        control.getFecha(),
                        control.getResultado()
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
                        control.getCliente().getNombre(),
                        control.getFecha(),
                        control.getResultado()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public void registrarEstudioClinico(RegistroEstudioClinicoDTO registroDTO) {
        // Validar el producto
        Optional<Producto> productoOpt = productoRepository.findById(registroDTO.getProductoId());
        if (productoOpt.isEmpty()) {
            throw new IllegalArgumentException("El producto con ID " + registroDTO.getProductoId() + " no existe.");
        }

        // Validar los métodos analíticos
        List<MetodoAnalitico> metodosAnaliticos = registroDTO.getMetodosAnaliticosIds().stream()
                .map(id -> metodoAnaliticoRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("El método analítico con ID " + id + " no existe.")))
                .collect(Collectors.toList());

        // Crear la entidad Control_Calidad
        Control_Calidad controlCalidad = new Control_Calidad();
        controlCalidad.setProducto(productoOpt.get());
        controlCalidad.setFecha(registroDTO.getFecha());
        controlCalidad.setResultado(registroDTO.getResultado());
        controlCalidad.setMetodosAnaliticos(metodosAnaliticos);

        // Guardar la entidad en la base de datos
        controlCalidadRepository.save(controlCalidad);
    }
}
