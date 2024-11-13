package com.megalabsapi.service;

import com.megalabsapi.model.entity.ReporteCumplimientoRegulatorio;

public interface ReporteCumplimientoRegulatorioService extends ReporteService<ReporteCumplimientoRegulatorio> {

    /**
     * Aprueba o desaprueba un reporte de cumplimiento regulatorio.
     *
     * @param id       el identificador del reporte
     * @param aprobado true si el reporte está aprobado, false si no está aprobado
     * @return el objeto ReporteCumplimientoRegulatorio actualizado
     */
    ReporteCumplimientoRegulatorio aprobarReporte(Integer id, boolean aprobado);

    /**
     * Genera un reporte en un formato adecuado (por ejemplo, PDF o HTML) para ser presentado a las autoridades.
     *
     * @param id el identificador del reporte
     * @return una URL o el contenido del reporte generado
     */
    String generarReporteParaAutoridades(Integer id);

    ReporteCumplimientoRegulatorio registrarAccionesCorrectivas(Integer id, String accionesCorrectivas);
}
