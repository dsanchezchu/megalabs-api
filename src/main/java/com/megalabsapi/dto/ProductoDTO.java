package com.megalabsapi.dto;

import com.megalabsapi.model.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDTO {
    private Integer idProducto;
    private Date fechaVenta;
    private String nombre;
    private StockStatus stock;
}
