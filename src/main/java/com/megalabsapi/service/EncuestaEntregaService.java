package com.megalabsapi.service;
import com.megalabsapi.model.entity.EncuestaEntrega;
import com.megalabsapi.repository.EncuestaEntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EncuestaEntregaService {

    @Autowired
    private EncuestaEntregaRepository encuestaRepository;

    public EncuestaEntrega guardarEncuesta(EncuestaEntrega encuesta) {
        return encuestaRepository.save(encuesta);
    }

    public List<EncuestaEntrega> obtenerTodasEncuestas() {
        return encuestaRepository.findAll();
    }

    public double calcularPromedio(String campo) {
        List<EncuestaEntrega> encuestas = encuestaRepository.findAll();
        return encuestas.stream()
                .mapToInt(encuesta -> {
                    switch (campo) {
                        case "puntualidadEntrega":
                            return encuesta.getPuntualidadEntrega();
                        case "estadoProducto":
                            return encuesta.getEstadoProducto();
                        case "profesionalismoPersonal":
                            return encuesta.getProfesionalismoPersonal();
                        case "facilidadContacto":
                            return encuesta.getFacilidadContacto();
                        default:
                            return 0;
                    }
                })
                .average()
                .orElse(0.0);
    }
}