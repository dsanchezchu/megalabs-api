package com.megalabsapi.dto;

import com.megalabsapi.model.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteVentasDTO {
    private Integer idProducto;
    private String nombreProducto;
    private StockStatus estadoStock;
    private Long totalVentas;
    private Double precioPromedio;
}
