package com.megalabsapi.service;
import com.megalabsapi.model.entity.EncuestaRecojo;
import com.megalabsapi.repository.EncuestaRecojoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EncuestaRecojoService {

    @Autowired
    private EncuestaRecojoRepository encuestaRecojoRepository;

    public EncuestaRecojo guardarEncuesta(EncuestaRecojo encuesta) {
        return encuestaRecojoRepository.save(encuesta);
    }

    public List<EncuestaRecojo> obtenerTodasEncuestas() {
        return encuestaRecojoRepository.findAll();
    }

    public double calcularPromedio(String campo) {
        List<EncuestaRecojo> encuestas = encuestaRecojoRepository.findAll();
        return encuestas.stream()
                .mapToInt(encuesta -> {
                    switch (campo) {
                        case "puntualidadRecojo":
                            return encuesta.getPuntualidadRecojo();
                        case "estadoProducto":
                            return encuesta.getEstadoProducto();
                        case "amabilidadPersonal":
                            return encuesta.getAmabilidadPersonal();
                        case "claridadInstrucciones":
                            return encuesta.getClaridadInstrucciones();
                        default:
                            return 0;
                    }
                })
                .average()
                .orElse(0.0);
    }
}