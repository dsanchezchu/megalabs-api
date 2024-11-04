package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.ReporteAuditoriaInterna;
import com.megalabsapi.model.enums.ImpactoAuditoria;
import com.megalabsapi.repository.ReporteAuditoriaInternaRepository;
import com.megalabsapi.service.ReporteAuditoriaInternaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteAuditoriaInternaServiceImpl extends ReporteServiceImpl<ReporteAuditoriaInterna>
        implements ReporteAuditoriaInternaService {

    @Autowired
    public ReporteAuditoriaInternaServiceImpl(ReporteAuditoriaInternaRepository repository) {
        super(repository);
    }

    @Override
    public ReporteAuditoriaInterna clasificarImpacto(Integer id, String impacto) {
        ReporteAuditoriaInterna auditoria = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Auditoría no encontrada"));

        try {
            // Convierte el String de entrada a ImpactoAuditoria
            auditoria.setImpacto(ImpactoAuditoria.valueOf(impacto.toUpperCase())); // Asegúrate de que el String coincida con el enum
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Impacto no válido. Los valores válidos son: BAJO, MEDIO, ALTO.");
        }

        return getRepository().save(auditoria);
    }


    @Override
    public ReporteAuditoriaInterna registrarInconformidadesYRecomendaciones(Integer id, String inconformidades, String recomendaciones) {
        ReporteAuditoriaInterna auditoria = getRepository().findById(id) // Usar getRepository()
                .orElseThrow(() -> new RuntimeException("Auditoría no encontrada"));
        auditoria.setInconformidades(inconformidades);
        auditoria.setRecomendaciones(recomendaciones);
        return getRepository().save(auditoria); // Usar getRepository()
    }
}
