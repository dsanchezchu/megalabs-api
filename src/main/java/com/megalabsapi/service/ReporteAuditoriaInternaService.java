package com.megalabsapi.service;

import com.megalabsapi.model.entity.ReporteAuditoriaInterna;

public interface ReporteAuditoriaInternaService extends ReporteService<ReporteAuditoriaInterna> {

    /**
     * Clasifica el impacto de una auditoría específica.
     *
     * @param id       el identificador de la auditoría
     * @param impacto  el nivel de impacto en formato String (BAJO, MEDIO, ALTO)
     * @return el objeto ReporteAuditoriaInterna actualizado
     */
    ReporteAuditoriaInterna clasificarImpacto(Integer id, String impacto);

    /**
     * Registra las inconformidades y recomendaciones para una auditoría específica.
     *
     * @param id               el identificador de la auditoría
     * @param inconformidades  las inconformidades encontradas en formato String
     * @param recomendaciones  las recomendaciones para corregir las inconformidades en formato String
     * @return el objeto ReporteAuditoriaInterna actualizado
     */
    ReporteAuditoriaInterna registrarInconformidadesYRecomendaciones(Integer id, String inconformidades, String recomendaciones);
}
