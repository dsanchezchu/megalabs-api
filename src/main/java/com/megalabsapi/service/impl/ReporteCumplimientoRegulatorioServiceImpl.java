package com.megalabsapi.service.impl;

import com.megalabsapi.model.entity.ReporteCumplimientoRegulatorio;
import com.megalabsapi.model.enums.EstadoReporte;
import com.megalabsapi.repository.ReporteCumplimientoRegulatorioRepository;
import com.megalabsapi.service.ReporteCumplimientoRegulatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
@Service
public class ReporteCumplimientoRegulatorioServiceImpl extends ReporteServiceImpl<ReporteCumplimientoRegulatorio>
        implements ReporteCumplimientoRegulatorioService {

    @Autowired
    public ReporteCumplimientoRegulatorioServiceImpl(ReporteCumplimientoRegulatorioRepository repository) {
        super(repository);
    }

    @Override
    public ReporteCumplimientoRegulatorio aprobarReporte(Integer id, boolean aprobado) {
        ReporteCumplimientoRegulatorio reporte = getRepository().findById(id) // Usar getRepository()
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        // Cambia el estado del reporte a APROBADO o NO_APROBADO
        reporte.setEstadoReporte(aprobado ? EstadoReporte.APROBADO : EstadoReporte.NO_APROBADO);

        return getRepository().save(reporte); // Usar getRepository()
    }

    @Override
    public String generarReporteParaAutoridades(Integer id) {
        ReporteCumplimientoRegulatorio reporte = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        try (PDDocument document = new PDDocument()) {
            // Crear una nueva página
            PDPage page = new PDPage();
            document.addPage(page);

            // Preparar el contenido
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Crear instancias de fuentes estándar
                PDType1Font fontBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font fontRegular = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

                contentStream.setFont(fontBold, 16);
                contentStream.beginText();
                contentStream.setLeading(20f);
                contentStream.newLineAtOffset(100, 700);

                // Agregar el título
                contentStream.showText("Reporte de Cumplimiento Regulatorio");
                contentStream.newLine();

                // Cambiar a texto normal
                contentStream.setFont(fontRegular, 12);

                // Agregar los detalles del reporte
                contentStream.showText("ID del Reporte: " + reporte.getId());
                contentStream.newLine();
                contentStream.showText("Estado del Reporte: " + reporte.getEstadoReporte());
                contentStream.newLine();
                contentStream.showText("Contenido de la Auditoría: " + reporte.getContenidoAuditoria());
                contentStream.newLine();
                contentStream.showText("Acciones Correctivas: " + reporte.getAccionesCorrectivas());
                contentStream.newLine();

                // Finalizar el contenido
                contentStream.endText();
            }

            // Escribir el PDF en un ByteArrayOutputStream
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                document.save(baos);

                // Convertir a Base64 para retornar como cadena
                return Base64.getEncoder().encodeToString(baos.toByteArray());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }

    @Override
    public ReporteCumplimientoRegulatorio registrarAccionesCorrectivas(Integer id, String accionesCorrectivas) {
        ReporteCumplimientoRegulatorio reporte = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        reporte.setAccionesCorrectivas(accionesCorrectivas);
        return getRepository().save(reporte);
    }
}
